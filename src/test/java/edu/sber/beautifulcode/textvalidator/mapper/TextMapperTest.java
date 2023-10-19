package edu.sber.beautifulcode.textvalidator.mapper;

import edu.sber.beautifulcode.textvalidator.dto.TextDto;
import edu.sber.beautifulcode.textvalidator.model.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TextMapperImpl.class})
public class TextMapperTest {
    @Autowired
    private TextMapper textMapper;

    @Test
    public void testTextDtoToTextMapping() {
        TextDto textDto = new TextDto();
        textDto.setText("text");

        Text text = textMapper.textDtoToText(textDto);

        Assertions.assertEquals(textDto.getText(), text.getText());
    }
}
