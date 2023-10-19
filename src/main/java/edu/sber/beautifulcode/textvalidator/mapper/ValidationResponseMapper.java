package edu.sber.beautifulcode.textvalidator.mapper;

import edu.sber.beautifulcode.textvalidator.dto.ValidationResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValidationResponseMapper {

    ValidationResponseDto booleanToValidationResponseDto(Boolean isCorrect);
}
