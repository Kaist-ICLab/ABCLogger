import { useEffect, useState } from "react";
import { NativeModules, SafeAreaView, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { G, Path, Svg } from "react-native-svg";
import LogoutModal from "../modals/LogoutModal";
import LoggingStatusModal from "../modals/LoggingStatusModal";

const SettingScreen: React.FC = () => {
    const [logoutModalVisible, setLogoutModalVisible] = useState(false);
    const [loggingStatusModalVisible, setLoggingStatusModalVisible] = useState(false);
    const [isLogging, setIsLogging] = useState(false);
    useEffect(() => {
        console.log(isLogging);
        if(isLogging){
            NativeModules.CollectorReactModule.start()
        }else{
            NativeModules.CollectorReactModule.stop()
        }
    },[isLogging])
    return (
        <SafeAreaView style={{ flex: 1 }}>
            <LogoutModal visible={logoutModalVisible}
                onLogout={() => {
                    NativeModules.AuthReactModule.logout()
                    setLogoutModalVisible(false)
                }}
                onRequestClose={() => { setLogoutModalVisible(false) }} />
            <LoggingStatusModal visible={loggingStatusModalVisible}
                onButtonClick={(status: boolean) => {
                    setLoggingStatusModalVisible(false)
                    setIsLogging(status)
                }}
                onRequestClose={() => { 
                    setLoggingStatusModalVisible(false)
                 }}
                currentStatus = {isLogging} />
            <View style={styles.header}>
                <Text style={styles.title}>Setting</Text>
            </View>
            <View style={styles.body}>
                <View style={styles.container}>
                    <Text style={styles.containerTitle}>General</Text>
                    <View style={styles.containerBody}>
                        <View style={styles.property}>
                            <View>
                                <Text style={styles.propertyName}>User name</Text>
                                <Text style={styles.propertyStatus}>{"Tester (tester@gmail.com)"}</Text>
                            </View>
                            <TouchableOpacity onPress={() => {
                                setLogoutModalVisible(true)
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
                <View style={styles.container}>
                    <Text style={styles.containerTitle}>Collector</Text>
                    <View style={styles.containerBody}>
                        <View style={styles.property}>
                            <View>
                                <Text style={styles.propertyName}>Logging status</Text>
                                <Text style={styles.propertyStatus}>{String(false)}</Text>
                            </View>
                            <TouchableOpacity onPress={() => {
                                setLoggingStatusModalVisible(true)
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