# ABCLogger

```
npx react-native start
npx react-native run-android
```

- [x] Logging Start / End 구현 ✅ 2024-01-18
    - TestCollector 작동여부 확인
    - dataReceived / timestamp
    - deviceId 및 subjectId 정상 작동 확인
        - subjectId: email
            - 이메일이 기록되는 지 확인
        - deviceId: SSAID 
            - 값이 일정하게 기록되는 지 확인
    - Sticky Notification 및 ForegroundService 생성
        - start에 생기고, stop에 없어지는 지 확인