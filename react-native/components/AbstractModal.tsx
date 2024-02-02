import { Modal, StyleSheet, Text, TouchableOpacity, View } from "react-native";

type Props = {
    description: string,
    visible: boolean,
    onRequestClose: () => void,
    onChangeConfirmed: () => void,
    children: React.ReactNode
}

const AbstractModal: React.FC<Props> = ({
    description,
    visible,
    onRequestClose,
    onChangeConfirmed,
    children,
}) => {
    return (
        <Modal
            animationType="fade"
            visible={visible}
            transparent={true}
            onRequestClose={onRequestClose}
        >
            <View style={styles.container}>
                <View style={styles.modal}>
                    <Text style={styles.modalTitle}>{description}</Text>
                    {children}
                    <View style={styles.buttonGroup}>
                        <TouchableOpacity style={styles.button} onPress={onChangeConfirmed}>
                            <Text style={styles.buttonText}>Apply</Text>
                        </TouchableOpacity>
                        <TouchableOpacity style={styles.button} onPress={onRequestClose}>
                            <Text style= {styles.buttonText}>Close</Text>
                        </TouchableOpacity>
                    </View>
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
        backgroundColor: "rgba(0,0,0,.7)"
    },
    modal: {
        width: "75%",
        padding: 15,
        flexDirection: "column",
        gap: 25,
        justifyContent: "center",
        borderRadius: 12,
        backgroundColor: "#fff",
    },
    modalTitle: {
        fontSize: 18,
    },
    buttonGroup: {
        alignSelf:"flex-end",
        flexDirection: "row",
        justifyContent:"flex-end",
        gap: 10,
    },
    button: {
        backgroundColor: "#017BCA",
        borderRadius: 6,
        padding: 8
    },
    buttonText: {
        fontSize: 14,
        fontWeight: "500",
        color: "#FFF",
    }
});

export default AbstractModal