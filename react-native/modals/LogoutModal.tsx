import { Modal, StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
    visible: boolean,
    onRequestClose: () => void,
    onLogout: () => void,
}

const LogoutModal: React.FC<Props> = ({ visible, onRequestClose, onLogout}) => {
    return (
        <Modal
            animationType="fade"
            visible={visible}
            transparent={true}
            onRequestClose={() => { onRequestClose() }}
        >
            <View style={styles.container}>
                <View style={styles.modal}>
                    <View>
                        <Text>
                            Do you want to sign out?{"\n"}
                            It will delete all locally saved data
                        </Text>
                    </View>
                    <TouchableOpacity style={styles.button} onPress={() => {onLogout()}}>
                        <Text style={styles.buttonText}>Sign out</Text>
                    </TouchableOpacity>
                </View>
            </View>
        </Modal>
    );
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: "100%",
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "rgba(255,255,255,.7)"
    },
    modal: {
        padding: 15,
        flexDirection: "column",
        gap: 25,
        justifyContent: "center",
        alignItems: "center",
        borderWidth: 1,
        borderColor: "#CCC",
        borderStyle: "solid",
        shadowColor: '#999',
        elevation: 2,
    },
    button: {
        backgroundColor: "#017BCA",
        borderRadius: 6,
        padding: 5
    },
    buttonText: {
        fontSize: 11,
        fontWeight: "700",
        color: "#FFF",
    }
});

export default LogoutModal