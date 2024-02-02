import { View, Text, StyleSheet } from "react-native"
import { RadioButton } from "react-native-paper"


interface RadioGroupProp {
    options: string[],
    value: string,
    setValue: (value: string) => void
}

const RadioGroup: React.FC<RadioGroupProp> = ({
    options,
    value,
    setValue
}) => {
    return (<RadioButton.Group
        onValueChange={setValue}
        value={value}
    >
        {options.map((option) =>
            <View style={styles.radioItem} key = {option}>
                <RadioButton value={option} />
                <Text>{option}</Text>
            </View>)
        }
    </RadioButton.Group>)
}

const styles = StyleSheet.create({
    radioItem: {
        flexDirection: "row",
        alignItems: "center"
    }
})




export default RadioGroup