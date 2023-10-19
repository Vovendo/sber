package edu.sber.beautifulcode.textvalidator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextDto {
    @NotNull(message = "Can't be null")
    @NotEmpty(message = "Can't be empty")
    @NotBlank(message = "Can't be blank")
    private String text;
}
