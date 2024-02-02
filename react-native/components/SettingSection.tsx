import { StyleSheet, Text, View } from "react-native"


interface SettingSectionProp {
    sectionTitle: string,
    children?: React.ReactNode
}   

const SettingSection: React.FC<SettingSectionProp> = ({
    sectionTitle,
    children
}) => {
    return (<View>
        <View style={styles.section}>
            <Text style={styles.sectionTitle}>{sectionTitle}</Text>
            <View style={styles.sectionBody}>
                {children}
            </View>
        </View>
    </View>)
}



const styles = StyleSheet.create({
    section: {
        flexDirection: "column",
        alignItems: "flex-start",
        gap: 10
    },
    sectionTitle: {
        color: "#017BCA",
        fontSize: 12,
        letterSpacing: .6,
        textTransform: "uppercase"
    },
    sectionBody: {
        paddingHorizontal: 10,
        width: "100%",
        flexDirection: "column",
        alignItems: "flex-start",
        gap: 10,
    }
});


export default SettingSection