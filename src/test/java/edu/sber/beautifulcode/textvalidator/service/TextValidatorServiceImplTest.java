package edu.sber.beautifulcode.textvalidator.service;

import edu.sber.beautifulcode.textvalidator.model.Text;
import edu.sber.beautifulcode.textvalidator.service.validation.TextValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        TextValidatorServiceImpl.class
})
public class TextValidatorServiceImplTest {

    private static final String TEXT_FOR_VALIDATION = "TEXT_FOR_VALIDATION";

    @Autowired
    private TextValidatorService textValidatorService;

    @MockBean
    private TextValidator textValidator;

    @Test
    public void textValidatorServiceTest() {
        Mockito.when(textValidator.isValid(TEXT_FOR_VALIDATION)).thenReturn(true);

        Text text = new Text(TEXT_FOR_VALIDATION);

        boolean expected = true;
        boolean actual = textValidatorService.isValid(text);

        Mockito.verify(textValidator).isValid(TEXT_FOR_VALIDATION);
        Assertions.assertEquals(expected, actual);
    }

}
