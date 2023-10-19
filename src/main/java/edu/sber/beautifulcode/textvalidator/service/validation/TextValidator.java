package edu.sber.beautifulcode.textvalidator.service.validation;

import org.springframework.stereotype.Component;

@Component
public class TextValidator implements Validator {
    @Override
    public boolean isValid(String text) {
        boolean containsEmptyBrackets = false;
        int unpairedOpeningBrackets = 0;

        for(int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                unpairedOpeningBrackets++;
                containsEmptyBrackets = true;
            } else if (text.charAt(i) == ')') {
                if (unpairedOpeningBrackets == 0 || containsEmptyBrackets) {
                    return false;
                }
                unpairedOpeningBrackets--;
            } else if (text.charAt(i) != ' ') {
                containsEmptyBrackets = false;
            }
        }

        return unpairedOpeningBrackets == 0;
    }
}
