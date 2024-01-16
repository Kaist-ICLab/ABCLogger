import { useState } from "react";
import { Modal, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { RadioButton } from "react-native-paper";

type Props = {
    visible: boolean,
    currentStatus: boolean,
    onRequestClose: () => void,
    onButtonClick: () => void,
}

const LoggingStatusModal: React.FC<Props> = ({ visible, onRequestClose, onButtonClick, currentStatus }) => {
    const [checked, setChecked] = useState(currentStatus);
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
                            Logging Status
                        </Text>
                        <RadioButton.Group
                            onValueChange={value => setChecked(value === "true")}
                            value={String(checked)}
                        >
                            <View style={{flexDirection:"row", alignItems:"center"}}>
                                <RadioButton value="true" />
                                <Text>First</Text>
                            </View>
                            <View style={{flexDirection:"row", alignItems:"center"}}>
                                <RadioButton value="false" />
                                <Text>Second</Text>
                            </View>
                        </RadioButton.Group>
                    </View>
                    <TouchableOpacity style={styles.button} onPress={() => { onButtonClick() }}>
                        <Text style={styles.buttonText}>Apply</Text>
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
    status: {
        color: "black",
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

export default LoggingStatusModal