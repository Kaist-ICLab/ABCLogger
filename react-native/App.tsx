import React, { useEffect, useState } from 'react';
import {
  Button,
  NativeEventEmitter,
  NativeModules,
  Text,
  View
} from 'react-native';


const App: React.FC = () => {
  const { AuthReactModule } = NativeModules;
  const [user, setUser] = useState<String>("");

  useEffect(()=> {
    const eventEmitter = new NativeEventEmitter(AuthReactModule)
    const userInfoListener = eventEmitter.addListener("userInfo", event => {
      setUser(event);
      console.log(event)
    });

    return () => {
      userInfoListener.remove();
    }
  },[])


  return <View>
    <Text>{user}</Text>
    <Button title='LOGIN' onPress={()=> {
      AuthReactModule.login()
      console.log("LOGIN")
    }}/>
    <Button title='LOGOUT'/>
  </View>
}

export default App;
