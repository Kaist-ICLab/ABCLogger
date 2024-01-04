import React, { useEffect, useState } from 'react';

import SettingScreen from './screens/SettingScreen';
import LoginScreen from './screens/LoginScreen';
import { NativeEventEmitter, NativeModules, SafeAreaView } from 'react-native';
import UpdateModal from './modals/UpdateModal';


type user = {
  isNull: boolean,
  name: string,
  email: string
}

const App: React.FC = () => {
  const [ currentUser, setCurrentUser] = useState<user>();
  const [modalVisible, setModalVisible] = useState(false);
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

    
  },[]);

  return (
    <SafeAreaView style ={{flex:1}}>
       {currentUser?.isNull? <LoginScreen/>: <SettingScreen/>}
       <UpdateModal visible = {modalVisible}
          onRequestClose={() => { setModalVisible(false)  }}
          onUpdate={() => {
            NativeModules.MaintenanceReactModule.update()
          }}
       />
    </SafeAreaView>
  )
  

}

export default App;
