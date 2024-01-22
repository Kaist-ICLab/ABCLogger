import React, { useEffect, useState } from 'react';

import { NativeEventEmitter, NativeModules, SafeAreaView, Text } from 'react-native';
import AbstractModal from './AbstractModal';
import LoginScreen from './screens/LoginScreen';
import SettingScreen from './screens/SettingScreen';


type user = {
  isNull: boolean,
  name: string,
  email: string
}

const App: React.FC = () => {
  const [currentUser, setCurrentUser] = useState<user>();
  const [modalVisible, setModalVisible] = useState(true);
  useEffect(() => {
    const eventEmitter = new NativeEventEmitter(NativeModules.AuthReactModule);
    let eventListener = eventEmitter.addListener('AuthState', event => {
      setCurrentUser(event);
      console.log(event);
    });

    NativeModules.MaintenanceReactModule.checkUpdate(() => {
      setModalVisible(true)
    })
    // Removes the listener once unmounted
    return () => {
      eventListener.remove();
    };
  }, []);

  return (
    <SafeAreaView style={{ flex: 1 }}>
      {currentUser?.isNull ? <LoginScreen /> : <SettingScreen />}
      <AbstractModal
        description='Update Released'
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)}
        onChangeConfirmed={() => NativeModules.MaintenanceReactModule.update()}
      >
        <Text>
          Updated Version Released! {"\n"}
          Please check download the app and update
        </Text>
      </AbstractModal>
    </SafeAreaView>
  )
}

export default App;
