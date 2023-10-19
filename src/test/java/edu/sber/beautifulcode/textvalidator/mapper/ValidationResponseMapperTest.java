package edu.sber.beautifulcode.textvalidator.mapper;

import edu.sber.beautifulcode.textvalidator.dto.ValidationResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ValidationResponseMapperImpl.class})
public class ValidationResponseMapperTest {
    @Autowired
    private ValidationResponseMapper validationResponseMapper;

    @Test
    public void testBooleanToValidationResponseDtoMapping() {
        Boolean isCorrect = true;

        ValidationResponseDto responseDto = validationResponseMapper.booleanToValidationResponseDto(isCorrect);

        Assertions.assertEquals(isCorrect, responseDto.getIsCorrect());
    }
}
