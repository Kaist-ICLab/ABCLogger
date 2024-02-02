import { SafeAreaView, StyleSheet, View } from "react-native";
import Header from "../components/Header";
import SettingSection from "../components/SettingSection";
import SettingSectionItem from "../components/SettingSectionItem";


const TestSettingScreen: React.FC = () => {
    return (
        <SafeAreaView style={{ flex: 1 }}>
            <Header title="Test" backButton={() => { }} />
            <View style={styles.body}>
                <SettingSection sectionTitle="General">
                    <SettingSectionItem propertyName="Permission" propertyStatus="Allowed" />
                    <SettingSectionItem propertyName="Status" propertyStatus="On" />
                    <SettingSectionItem propertyName="Collection Frequency" propertyStatus="30 minutes" />
                </SettingSection>
                <SettingSection sectionTitle="Filter">
                </SettingSection>
                <SettingSection sectionTitle="Data">
                    <SettingSectionItem propertyName="Started time" propertyStatus="2023-12-27 00:00" />
                    <SettingSectionItem propertyName="Last collected time" propertyStatus="2023-12-31 22:30" />
                    <SettingSectionItem propertyName="Amount of collected data" propertyStatus="36 records" />
                    <SettingSectionItem propertyName="Flush data" />
                </SettingSection>
                <SettingSection sectionTitle="How">
                    <SettingSectionItem propertyName="The data is collected without any API"/>
                </SettingSection>
            </View>
        </SafeAreaView>
    );
}


const styles = StyleSheet.create({
    body: {
        flex: 1,
        padding: 15,
        flexDirection: "column",
        alignItems: "flex-start",
        gap: 20
    }
});

export default TestSettingScreen