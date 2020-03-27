package services;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Providers {

    private Providers(){

    }

    public static Stream<Arguments> testAdditionNumbers() {
        return Stream.of(
                arguments(124.1, 222.2, 346.3),
                arguments(22.0, -102.0, -80.0),
                arguments(0.1, 0.0, 0.1),
                arguments(-200000000.0, 200000000.0, 0.0)
        );
    }

    public static Stream<Arguments> testSubtractionNumbers() {
        return Stream.of(
                arguments(2424.01, 2424.02, -0.01),
                arguments(0.0, -1.0, 1.0),
                arguments(200000.1, 0.0, 200000.1),
                arguments(1.1, 2000000000.0 , -1999999998.9)
        );
    }

    public static Stream<Arguments> testDivideNumbers() {
        return Stream.of(
                arguments(8.0, 4.0, 2.0),
                arguments(1.0, 0.1 , 10.0),
                arguments(0.0, 20.0, 0.0),
                arguments(-45.0 , 5.0 , -9.0)
        );
    }

    public static Stream<Arguments> testMultiplyNumbers() {
        return Stream.of(
                arguments(8.0, 4.0, 32.0),
                arguments(1.0, 0.1 , 0.1),
                arguments(0.0, 20.0, 0.0),
                arguments(-4.0 , 5.0 , -20.0)
        );
    }

    public static Stream<Arguments> testCalcExpression() {
        return Stream.of(
                //arguments("(2+2)*2", "8.0"),
                arguments("5*8*(2+9)+(7*5+8-9*(5*5)+5)", "263.0"),
                arguments("((25+25)+50)/10", "10.0"),
                arguments("200-100*(2/10)", "180.0"),
                arguments("25-2*(42/2)" , "-17.0")
        );
    }

    public static Stream<Arguments> testCalcExpressionWithTwoOperands() {
        return Stream.of(
                arguments(2.0, 2.6, "-", 0.6),
                arguments(20.1, 13.0, "+", 33.1),
                arguments(2.0, 22.0, "/", 11.0),
                arguments(10.1, 2.0, "*", 20.2)
        );
    }

    public static Stream<Arguments> testMapInfixNotationToPostfixNotation() {
        return Stream.of(
                arguments("(2+2)*2", new ArrayDeque<>(Arrays.asList("2", "2", "+", "2", "*"))),
                arguments("5*8*(2+9)+(7*5+8-9*(5*5)+5)",new ArrayDeque<>(Arrays.asList("5", "8", "*", "2", "9", "+", "*", "7", "5", "*", "8", "+", "9", "5", "5", "*", "*", "-", "5", "+", "+"))),
                arguments("((25+25)+50)/10", new ArrayDeque<String>(Arrays.asList("25", "25", "+", "50", "+", "10", "/"))),
                arguments("25-2*(42/2)", new ArrayDeque<String>(Arrays.asList("25", "2", "42", "2", "/", "*", "-")))
        );
    }

    public static Stream<Arguments> testCheckExpressionContainsLetters() {
        return Stream.of(
                arguments("(2+2)+d*2"),
                arguments("в+5*8*(2+9)+(7*5+8-9*(5*5)+5)"),
                arguments("продам + гараж"),
                arguments("a+b*c")
        );
    }

    public static Stream<Arguments> testCheckCountBrackets() {
        return Stream.of(
                arguments("(2+2)+2)*2"),
                arguments("3+(5*8*(2+9)+(7*5+8-9*(5*5)+5)"),
                arguments("(7+1)*5)")
        );
    }
}
