package edu.sber.beautifulcode.textvalidator.mapper;

import edu.sber.beautifulcode.textvalidator.dto.TextDto;
import edu.sber.beautifulcode.textvalidator.model.Text;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TextMapper {

    Text textDtoToText(TextDto textDto);
}
