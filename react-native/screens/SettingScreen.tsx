import { useState } from "react";
import { SafeAreaView, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { G, Path, Svg } from "react-native-svg";
import LogoutModal from "../modals/LogoutModal";

const SettingScreen: React.FC = () => {
    const [modalVisible, setModalVisible] = useState(false);
    return (
        <SafeAreaView style={{ flex: 1 }}>
            <LogoutModal visible={modalVisible} 
                onLogout = {() => {console.log("sign out")}}
                onRequestClose={() => {setModalVisible(false)}} />
            <View style={styles.header}>
                <Text style={styles.title}>Setting</Text>
            </View>
            <View style={styles.body}>
                <Text style={styles.containerTitle}>General</Text>
                <View style={styles.container}>
                    <View style={styles.property}>
                        <View>
                            <Text style={styles.propertyName}>User name</Text>
                            <Text style={styles.propertyStatus}>{"Tester (tester@gmail.com)"}</Text>
                        </View>
                        <TouchableOpacity onPress = {() => {
                            setModalVisible(true)
                        }}>
                            <Svg fill="none" width="28" height="28">
                                <G clip-path="url(#clip0_14_823)">
                                    <Path d="M10.0217 19.355L15.365 14L10.0217 8.645L11.6667 7L18.6667 14L11.6667 21L10.0217 19.355Z" fill="#434343" />
                                </G>
                            </Svg>
                        </TouchableOpacity>

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
        gap: 10
    },
    containerTitle: {
        color: "#017BCA",
        fontSize: 12,
        letterSpacing: .6,
        textTransform: "uppercase"
    },
    container: {
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