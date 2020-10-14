package services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalcServiceImplNegativeTest {
    private static CalcService calcService;


    @BeforeAll
    static void setup(){
        calcService = new CalcServiceImpl();
    }

    @Test
    @DisplayName("Тест метода divideNumbers класса CalcServiceImpl  проверка деления на ноль")
    void divideNumbersDivisionByZeroTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calcService.divideNumbers(1.0,0.0),
                "Ожидалось исключение IllegalArgumentException, но его не произошло"
        );
        assertTrue(thrown.getMessage().contains("на ноль делить нельзя"));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testCheckExpressionContainsLetters")
    @DisplayName("Тест метода checkExpressionContainsLetters класса CalcServiceImpl  проверка на схождение букв")
    void checkExpressionContainsLettersTest(String expression) {
            IllegalArgumentException thrown = assertThrows(
                    IllegalArgumentException.class,
                    () -> calcService.checkExpressionContainsLetters(expression),
                    "Ожидалось исключение IllegalArgumentException, но его не произошло"
            );
            assertTrue(thrown.getMessage().contains("Выражение не должно содержать буквы"));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testCheckCountBrackets")
    @DisplayName("Тест метода checkCountBrackets класса CalcServiceImpl  проверка на парное количество скобок")
    void checkCountBracketsTest(String expression) {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> calcService.checkCountBrackets(expression),
                "Ожидалось исключение IllegalArgumentException, но его не произошло"
        );
        assertTrue(thrown.getMessage().contains("Несовпадение количества скобок!"));
    }
}