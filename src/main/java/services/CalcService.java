package services;

import java.util.Deque;
import java.util.Optional;

public interface CalcService {

    String calcPostfixNotation(Deque<String> queue);

    Optional<Double> calcExpressionWithTwoOperands(Double operandOne, Double operandTwo, String operationSign);

    Deque<String> mapInfixNotationToPostfixNotation(String expression);

    void checkCountBrackets(String expression);

    void checkExpressionContainsLetters(String expression);

    Double additionNumbers(Double operandOne, Double operandTwo);

    Double subtractionNumbers(Double operandOne, Double operandTwo);

    Double divideNumbers(Double operandOne, Double operandTwo);

    Double multiplyNumbers(Double operandOne, Double operandTwo);

    String calcExpression(String expression);

}
