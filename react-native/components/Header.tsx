import { StyleSheet, Text, TouchableOpacity, View } from "react-native"
import { G, Path, Svg } from "react-native-svg";


interface HeaderProp {
    title: string
    backButton?: () => void
}

const Header: React.FC<HeaderProp> = ({
    title, backButton
}) => {
    return (
        <View style={styles.header}>
            {backButton ?
                <TouchableOpacity onPress={backButton}>
                    <Svg fill="none" width="28" height="28">
                        <G clip-path="url(#clip0_14_823)">
                            <Path d="M10.0217 19.355L15.365 14L10.0217 8.645L11.6667 7L18.6667 14L11.6667 21L10.0217 19.355Z" fill="#434343" />
                        </G>
                    </Svg>
                </TouchableOpacity> : null
            }
            <Text style={styles.title}>{title}</Text>
        </View>
    )
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
});

export default Header