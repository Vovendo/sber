package edu.sber.beautifulcode.textvalidator.controller;

import edu.sber.beautifulcode.textvalidator.dto.TextDto;
import edu.sber.beautifulcode.textvalidator.dto.ValidationResponseDto;
import edu.sber.beautifulcode.textvalidator.mapper.TextMapper;
import edu.sber.beautifulcode.textvalidator.mapper.ValidationResponseMapper;
import edu.sber.beautifulcode.textvalidator.model.Text;
import edu.sber.beautifulcode.textvalidator.service.TextValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TextControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private TextController textController;
    @Mock
    private TextValidatorService textValidatorService;
    @Mock
    private TextMapper textMapper;
    @Mock
    private ValidationResponseMapper validationResponseMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(textController).build();
    }

    @Test
    public void testCheckBrackets() throws Exception {
        TextDto requestDto = new TextDto();
        requestDto.setText("()");

        Text mappedText = new Text();
        mappedText.setText("()");

        ValidationResponseDto responseDto = new ValidationResponseDto();
        responseDto.setIsCorrect(true);

        when(textMapper.textDtoToText(any(TextDto.class))).thenReturn(mappedText);
        when(textValidatorService.isValid(any(Text.class))).thenReturn(true);
        when(validationResponseMapper.booleanToValidationResponseDto(true)).thenReturn(responseDto);

        mockMvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"()\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect").value(true));

        verify(textMapper, times(1)).textDtoToText(any(TextDto.class));
        verify(textValidatorService, times(1)).isValid(any(Text.class));
        verify(validationResponseMapper, times(1)).booleanToValidationResponseDto(true);
    }
}
