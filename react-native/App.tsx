import React, { useEffect, useState } from 'react';

import SettingScreen from './screens/SettingScreen';
import LoginScreen from './screens/LoginScreen';
import { NativeEventEmitter, NativeModules } from 'react-native';


type user = {
  isNull: boolean,
  name: string,
  email: string
}

const App: React.FC = () => {
  const [ currentUser, setCurrentUser] = useState<user>();
  useEffect(() => {
    const eventEmitter = new NativeEventEmitter(NativeModules.AuthReactModule);
    let eventListener = eventEmitter.addListener('AuthState', event => {
      setCurrentUser(event)
      console.log(event)
    });

    // Removes the listener once unmounted
    return () => {
      eventListener.remove();
    };
  })

  return (
      currentUser?.isNull? <LoginScreen/>: <SettingScreen/>
  )
  

}

export default App;
