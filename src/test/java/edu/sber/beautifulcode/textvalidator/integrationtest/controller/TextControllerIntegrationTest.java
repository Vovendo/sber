package edu.sber.beautifulcode.textvalidator.integrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sber.beautifulcode.textvalidator.controller.TextController;
import edu.sber.beautifulcode.textvalidator.controller.exception.ApiError;
import edu.sber.beautifulcode.textvalidator.mapper.TextMapper;
import edu.sber.beautifulcode.textvalidator.mapper.ValidationResponseMapper;
import edu.sber.beautifulcode.textvalidator.model.Text;
import edu.sber.beautifulcode.textvalidator.service.TextValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TextController.class)
@MockBeans({@MockBean(TextMapper.class), @MockBean(ValidationResponseMapper.class)})
public class TextControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TextValidatorService textValidatorService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        when(textValidatorService.isValid(new Text("valid_text"))).thenReturn(true);
    }

    @Test
    public void whenRequestIsValid_thenReturns200() throws Exception {
        mockMvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"valid_text\"}"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenRequestIsInvalid_thenReturns400() throws Exception {
        mockMvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString();
                    ApiError apiError = objectMapper.readValue(content, ApiError.class);
                    List<String> errors = apiError.getErrors();
                    assertTrue(errors.contains("text: Can't be null"));
                })
                .andDo(print());
    }
}

