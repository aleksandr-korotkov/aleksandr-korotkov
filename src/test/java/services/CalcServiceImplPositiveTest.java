package services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Deque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Позитивные тест кейсы для класса CalcServiceImpl")
class CalcServiceImplPositiveTest {
    private static CalcService calcService;

    @BeforeAll
    static void setup(){
        calcService = new CalcServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testAdditionNumbers")
    @DisplayName("Тест метода additionNumbers класса CalcServiceImpl")
    void additionNumbersTest(Double value1, Double value2, Double expected) {
        assertThat(calcService.additionNumbers(value1,value2),closeTo(expected,0.005));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testSubtractionNumbers")
    @DisplayName("Тест метода subtractionNumbers класса CalcServiceImpl")
    void subtractionNumbersTest(Double value1, Double value2, Double expected) {
        assertThat(calcService.subtractionNumbers(value1,value2),closeTo(expected,0.005));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testDivideNumbers")
    @DisplayName("Тест метода divideNumbers класса CalcServiceImpl")
    void divideNumbersTest(Double operandOne, Double operandTwo, Double expected) {
        assertThat(calcService.divideNumbers(operandOne, operandTwo), closeTo(expected,0.05));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testMultiplyNumbers")
    @DisplayName("Тест метода multiplyNumbers класса CalcServiceImpl")
    void multiplyNumbersTest(Double operandOne, Double operandTwo, Double expected) {
        assertThat(calcService.multiplyNumbers(operandOne, operandTwo), closeTo(expected,0.05));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testCalcExpression")
    @DisplayName("Тест метода calcExpression класса CalcServiceImpl")
    void calcExpressionTest(String expression, String expected) {
        assertThat(calcService.calcExpression(expression), is(equalTo(expected)));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testCalcExpressionWithTwoOperands")
    @DisplayName("Тест метода calcExpressionWithTwoOperands класса CalcServiceImpl")
    void calcExpressionWithTwoOperandsTest(Double operandOne , Double operandTwo, String operationSign, Double expected) {
        assertThat(calcService.calcExpressionWithTwoOperands(operandOne, operandTwo, operationSign).get(), closeTo(expected,0.05));
    }

    @ParameterizedTest
    @MethodSource("services.Providers#testMapInfixNotationToPostfixNotation")
    @DisplayName("Тест метода mapInfixNotationToPostfixNotation класса CalcServiceImpl")
    void mapInfixNotationToPostfixNotationTest(String expression, Deque<String> expected) {
        assertArrayEquals(calcService.mapInfixNotationToPostfixNotation(expression).toArray(), expected.toArray());
    }


}