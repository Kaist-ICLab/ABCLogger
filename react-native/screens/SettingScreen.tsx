import { useEffect, useState } from "react";
import { SafeAreaView, StyleSheet, Text, View } from "react-native";
import AbstractModal from "../components/AbstractModal";
import Header from "../components/Header";
import RadioGroup from "../components/RadioGroup";
import SettingSection from "../components/SettingSection";
import SettingSectionItem from "../components/SettingSectionItem";



type AuthInfoProps = {
    name: String,
    email: String
}

const AuthInfoComponent: React.FC<AuthInfoProps> = ({ name, email }) => {
    const [isModalVisible, setModalVisible] = useState(false);
    return (
        <View>
            <AbstractModal
                description={"Logout"}
                visible={isModalVisible}
                onRequestClose={() => setModalVisible(false)}
                onChangeConfirmed={() => { console.log("button Clicked") }}
            >
                <Text>{
                    "Do you really want to sign out?" + "\n" +
                    "It will delete all locally saved data"
                }</Text>
            </AbstractModal>
            <SettingSectionItem
                propertyName="User name"
                propertyStatus={`${name}(${email})`}
                onClick={() => setModalVisible(true)}
            />
        </View>
    )
}

const CollectorStatusComponent: React.FC = ({ }) => {
    const [isModalVisible, setModalVisible] = useState(false);
    const [collectorStatus, setCollectorStatus] = useState("Off");
    let prevCollectorStatus = "Off";
    useEffect(() => {
        if (isModalVisible) {
            prevCollectorStatus = collectorStatus
        }
    }, [isModalVisible]);
    useEffect(() => {
        // isCollecting?
        //     NativeModules.CollectorReactModule.start():
        //     NativeModules.CollectorReactModule.stop()
    }, [setCollectorStatus])
    return (
        <View>
            <AbstractModal
                description={"Collection Status"}
                visible={isModalVisible}
                onRequestClose={() => {
                    setModalVisible(false)
                    setCollectorStatus(prevCollectorStatus)
                }}
                onChangeConfirmed={() => { console.log("button Clicked") }}
            >
                <RadioGroup
                    options={["ON", "OFF"]}
                    value={collectorStatus}
                    setValue={setCollectorStatus} />
            </AbstractModal>
            <SettingSectionItem
                propertyName="Collector Status"
                propertyStatus={collectorStatus}
                onClick={() => setModalVisible(true)}
            />
        </View>
    )
}

const SettingScreen: React.FC = () => {
    return (
        <SafeAreaView style={{ flex: 1 }}>
            <Header title="Setting" />
            <View style={styles.body}>
                <SettingSection sectionTitle="General">
                    <AuthInfoComponent email={"email"} name={"name"} />
                </SettingSection>
                <SettingSection sectionTitle="Auto-sync">
                    <CollectorStatusComponent />
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

export default SettingScreen