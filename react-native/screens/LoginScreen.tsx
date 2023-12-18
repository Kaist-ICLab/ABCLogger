import { SafeAreaView, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import LinearGradient from "react-native-linear-gradient";
import Svg, { Path } from "react-native-svg";


const LoginScreen: React.FC = () => {
    return (
        <SafeAreaView style={{ flex: 1 }} >
            <LinearGradient start={{ x: 0, y: 0 }} end={{ x: 0, y: 1 }} colors={['#007AC9', '#49B5FA']} style={{ flex: 1 }}>
                <View style={{ paddingHorizontal: 30, paddingVertical: 100, flex: 1, justifyContent: "space-between" }}>
                    <View style={{ width: "100%", justifyContent: "center", alignItems: "center" }}>
                        <Text style={{ fontSize: 44, color: "white", fontWeight: "500" }}>{"ABC Logger"}</Text>
                        <Text style={{ fontSize: 14, color: "white" }}>{"Record your daily life"}</Text>
                    </View>
                    <TouchableOpacity
                        onPress={() => { }}
                        style={styles.loginButton}>
                        <Svg width="24" height="24" viewBox="0 0 24 24" fill="none">
                            <Path d="M22.56 12.25C22.56 11.47 22.49 10.72 22.36 10H12V14.26H17.92C17.66 15.63 16.88 16.79 15.71 17.57V20.34H19.28C21.36 18.42 22.56 15.6 22.56 12.25Z" fill="#4285F4" />
                            <Path d="M12 23C14.97 23 17.46 22.02 19.28 20.34L15.71 17.57C14.73 18.23 13.48 18.63 12 18.63C9.13999 18.63 6.70999 16.7 5.83999 14.1H2.17999V16.94C3.98999 20.53 7.69999 23 12 23Z" fill="#34A853" />
                            <Path d="M5.84 14.09C5.62 13.43 5.49 12.73 5.49 12C5.49 11.27 5.62 10.57 5.84 9.91001V7.07001H2.18C1.43 8.55001 1 10.22 1 12C1 13.78 1.43 15.45 2.18 16.93L5.03 14.71L5.84 14.09Z" fill="#FBBC05" />
                            <Path d="M12 5.38C13.62 5.38 15.06 5.94 16.21 7.02L19.36 3.87C17.45 2.09 14.97 1 12 1C7.69999 1 3.98999 3.47 2.17999 7.07L5.83999 9.91C6.70999 7.31 9.13999 5.38 12 5.38Z" fill="#EA4335" />
                        </Svg>
                        <Text>{"Sign in with Google"}</Text>
                    </TouchableOpacity>
                </View>

            </LinearGradient>
        </SafeAreaView>
    );
}


const styles = StyleSheet.create({
    main: {
        display: "flex",
        paddingHorizontal: 30,
        paddingVertical: 100,
        flexDirection: "column",
        justifyContent: "space-between",
        alignItems: "center"
    },
    loginButton: {
        backgroundColor: "white",
        display: "flex",
        flexDirection: "row",
        padding: 10,
        justifyContent: "center",
        alignItems: "center",
        gap: 10,
        borderRadius: 8,
        elevation: 4
    }
});

export default LoginScreen