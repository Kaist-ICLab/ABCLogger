import { StyleSheet, Text, TouchableOpacity, View } from "react-native"
import { G, Path, Svg } from "react-native-svg";


interface SettingSectionItemProp {
    propertyName: string,
    propertyStatus?: string,
    onClick?: () => void,
}

const SettingSectionItem: React.FC<SettingSectionItemProp> = ({
    propertyName,
    propertyStatus,
    onClick
}) => {
    return (
        <View style={styles.property}>
            <View>
                <Text style={styles.propertyName}>{propertyName}</Text>
                {propertyStatus? <Text style={styles.propertyStatus}>{propertyStatus}</Text>:null}
            </View>
            {onClick ? <TouchableOpacity onPress={onClick}>
                <Svg fill="none" width="28" height="28">
                    <G clip-path="url(#clip0_14_823)">
                        <Path d="M10.0217 19.355L15.365 14L10.0217 8.645L11.6667 7L18.6667 14L11.6667 21L10.0217 19.355Z" fill="#434343" />
                    </G>
                </Svg>
            </TouchableOpacity> : null}
        </View>
    )
}



const styles = StyleSheet.create({
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

export default SettingSectionItem