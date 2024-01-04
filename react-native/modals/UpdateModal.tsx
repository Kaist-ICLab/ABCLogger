import { Modal, StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
    visible: boolean,
    onRequestClose: () => void,
    onUpdate: () => void,
}

const UpdateModal: React.FC<Props> = ({ visible, onRequestClose, onUpdate}) => {
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
                            Updated Version Released! {"\n"}
                            Please check download the app and update
                        </Text>
                    </View>
                    <TouchableOpacity style={styles.button} onPress={() => {onUpdate()}}>
                        <Text style={styles.buttonText}>Download</Text>
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
        marginHorizontal: 30,
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

export default UpdateModal