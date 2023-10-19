package edu.sber.beautifulcode.textvalidator.service;

import edu.sber.beautifulcode.textvalidator.model.Text;
import edu.sber.beautifulcode.textvalidator.service.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextValidatorServiceImpl implements TextValidatorService {

    private final List<Validator> validators;

    @Override
    public boolean isValid(Text text) {
        return validators.stream().allMatch(validator -> validator.isValid(text.getText()));
    }
}
