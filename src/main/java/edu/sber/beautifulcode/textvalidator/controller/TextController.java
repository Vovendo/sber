package edu.sber.beautifulcode.textvalidator.controller;

import edu.sber.beautifulcode.textvalidator.dto.TextDto;
import edu.sber.beautifulcode.textvalidator.dto.ValidationResponseDto;
import edu.sber.beautifulcode.textvalidator.mapper.TextMapper;
import edu.sber.beautifulcode.textvalidator.mapper.ValidationResponseMapper;
import edu.sber.beautifulcode.textvalidator.model.Text;
import edu.sber.beautifulcode.textvalidator.service.TextValidatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TextController {
    private final TextValidatorService textValidatorService;
    private final TextMapper textMapper;
    private final ValidationResponseMapper validationResponseMapper;

    @PostMapping("/checkBrackets")
    public ValidationResponseDto checkBrackets(@Valid @RequestBody TextDto textDto) {
        Text text = textMapper.textDtoToText(textDto);
        return validationResponseMapper.booleanToValidationResponseDto(textValidatorService.isValid(text));
    }
}
