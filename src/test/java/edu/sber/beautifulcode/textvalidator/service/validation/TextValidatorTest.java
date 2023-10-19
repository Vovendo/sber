package edu.sber.beautifulcode.textvalidator.service.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TextValidatorTest {
    @ParameterizedTest
    @MethodSource("testBracketsSource")
    void testBrackets(ArgumentsAccessor accessor) {
        List<Object> args = accessor.toList();
        String text = (String) args.get(0);
        Boolean expectedResult = (Boolean) args.get(1);

        TextValidator textValidator = new TextValidator();
        boolean actualResult = textValidator.isValid(text);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    static Stream<Arguments> testBracketsSource() {
        return Stream.of(
                arguments(
                        "()",
                        false
                ),
                arguments(
                        "(   )",
                        false
                ),
                arguments(
                        ")(",
                        false
                ),
                arguments(
                        "(",
                        false
                ),
                arguments(
                        ")",
                        false
                ),
                arguments(
                        "(text)",
                        true
                ),
                arguments(
                        "(text())",
                        false
                ),
                arguments(
                        "(text( ))",
                        false
                ),
                arguments(
                        "(text( text))",
                        true
                ),
                arguments(
                        "(text( text ))",
                        true
                ),
                arguments(
                        "(text(text))",
                        true
                ),
                arguments(
                        "(text(text)) ",
                        true
                ),
                arguments(
                        "(text(text)) ()",
                        false
                ),
                arguments(
                        "(text( ) text )",
                        false
                )
        );
    }
}
