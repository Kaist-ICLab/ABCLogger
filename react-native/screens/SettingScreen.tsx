import { useEffect, useState } from "react";
import { NativeModules, SafeAreaView, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { G, Path, Svg } from "react-native-svg";
import AbstractModal from "../AbstractModal";
import { RadioButton } from "react-native-paper";



type AuthInfoProps = {
    name: String,
    email: String
}

const AuthInfoComponent: React.FC<AuthInfoProps> = ({ name, email }) => {
    const [isModalVisible, setModalVisible] = useState(false);
    return (
        <View style={styles.property}>
            <AbstractModal
                description={"Logout"}
                visible={isModalVisible}
                onRequestClose={() => setModalVisible(false)}
                onChangeConfirmed={() => { console.log("button Clicked") }}
            >
                <Text>{
                    "Do you really want to sign out?" + "\n" +
                    "It will delete all locally saved data"
                }</Text>
            </AbstractModal>
            <View>
                <Text style={styles.propertyName}>User name</Text>
                <Text style={styles.propertyStatus}>{`${name}(${email})`}</Text>
            </View>
            <TouchableOpacity onPress={() => {
                setModalVisible(true)
            }}>
                <Svg fill="none" width="28" height="28">
                    <G clip-path="url(#clip0_14_823)">
                        <Path d="M10.0217 19.355L15.365 14L10.0217 8.645L11.6667 7L18.6667 14L11.6667 21L10.0217 19.355Z" fill="#434343" />
                    </G>
                </Svg>
            </TouchableOpacity>
        </View>
    )
}

const CollectorStatusComponent: React.FC = ({ }) => {
    const [isModalVisible, setModalVisible] = useState(false);
    const [isCollecting, setCollecting] = useState(false);
    let prevCollector = false; 
    useEffect(() => {
        if(isModalVisible){
            prevCollector = isCollecting
        }
    },[isModalVisible])
    useEffect(() => {
        // isCollecting?
        //     NativeModules.CollectorReactModule.start():
        //     NativeModules.CollectorReactModule.stop()
    }, [isCollecting])
    return (
        <View style={styles.property}>
            <AbstractModal
                description={"Collection Status"}
                visible={isModalVisible}
                onRequestClose={() => {
                    setModalVisible(false)
                    setCollecting(prevCollector)
                }}
                onChangeConfirmed={() => { console.log("button Clicked") }}
            >
                <RadioButton.Group
                    onValueChange={value => setCollecting(value === "true")}
                    value={String(isCollecting)}
                >
                    <View style={{flexDirection:"row", alignItems:"center"}}>
                        <RadioButton value="true" />
                        <Text>On</Text>
                    </View>
                    <View style={{flexDirection:"row", alignItems:"center"}}>
                        <RadioButton value="false" />
                        <Text>Off</Text>
                    </View>
                </RadioButton.Group>
            </AbstractModal>
            <View>
                <Text style={styles.propertyName}>Collector Status</Text>
                <Text style={styles.propertyStatus}>{isCollecting? "ON": "OFF"}</Text>
            </View>
            <TouchableOpacity onPress={() => {
                setModalVisible(true)
            }}>
                <Svg fill="none" width="28" height="28">
                    <G clip-path="url(#clip0_14_823)">
                        <Path d="M10.0217 19.355L15.365 14L10.0217 8.645L11.6667 7L18.6667 14L11.6667 21L10.0217 19.355Z" fill="#434343" />
                    </G>
                </Svg>
            </TouchableOpacity>
        </View>
    )
}

const SettingScreen: React.FC = () => {
    return (
        <SafeAreaView style={{ flex: 1 }}>
            <View style={styles.header}>
                <Text style={styles.title}>Setting</Text>
            </View>
            <View style={styles.body}>
                <View style={styles.container}>
                    <Text style={styles.containerTitle}>General</Text>
                    <View style={styles.containerBody}>
                        <AuthInfoComponent email={"email"} name={"name"} />
                    </View>
                </View>
                <View style={styles.container}>
                    <Text style={styles.containerTitle}>Collector</Text>
                    <View style={styles.containerBody}>
                        <CollectorStatusComponent/>
                    </View>
                </View>
            </View>
        </SafeAreaView>
    );
}


const styles = StyleSheet.create({
    header: {
        backgroundColor: "#666",
        paddingVertical: 12,
        paddingHorizontal: 15,
        alignItems: "flex-start"
    },
    title: {
        fontSize: 24,
        fontWeight: "600",
        color: "#FFF"
    },
    body: {
        flex: 1,
        padding: 15,
        flexDirection: "column",
        alignItems: "flex-start",
        gap: 20
    },
    container: {
        flexDirection: "column",
        alignItems: "flex-start",
        gap: 10
    },
    containerTitle: {
        color: "#017BCA",
        fontSize: 12,
        letterSpacing: .6,
        textTransform: "uppercase"
    },
    containerBody: {
        paddingHorizontal: 10,
        width: "100%",
        flexDirection: "column",
        alignItems: "flex-start",
        gap: 10,
    },
    property: {
        flexDirection: "row",
        width: "100%",
        justifyContent: "space-between",
        alignItems: "center",
    },
    propertyName: {
        color: "#333",
        fontSize: 15,
    },
    propertyStatus: {
        color: "#666",
        fontSize: 13,
    },
    propertyChange: {

    }
});

export default SettingScreen