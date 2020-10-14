package services;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcServiceImpl implements CalcService {
    private static final String REGEX_OPERATION_SINGS = "[\\+\\-\\*\\/]";
    private static final String REGEX_NUMBER = "\\d+";
    private static final String REGEX_LETTERS = "[a-zA-Zа-яА-Я]";
    private static final String LEFT_BRACKET = "(";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVIDE = "/";
    private static final String MULTIPLY = "*";

    @Override
    public String calcExpression(String expression) {
        checkCountBrackets(expression);
        checkExpressionContainsLetters(expression);
        Deque<String> postfixNotation = mapInfixNotationToPostfixNotation(expression);
        return calcPostfixNotation(postfixNotation);
    }

    @Override
    public String calcPostfixNotation(Deque<String> queue){
        Stack<Double> doubles = new Stack<>();

        for (String elementOfQueue : queue){

            if(elementOfQueue.matches(REGEX_NUMBER)){
                doubles.push(Double.parseDouble(elementOfQueue));
                continue;
            }

            if(elementOfQueue.matches(REGEX_OPERATION_SINGS)){
                doubles.push(calcExpressionWithTwoOperands(doubles.pop(),doubles.pop(),elementOfQueue).get());
            }
        }
        return String.valueOf(doubles.peek());
    }

    @Override
    public Optional<Double> calcExpressionWithTwoOperands(Double operandOne , Double operandTwo, String operationSign){
        Double resultOfOperation = null;
        if(operationSign.equals(PLUS)){
           resultOfOperation = additionNumbers(operandTwo, operandOne);
        }
        if(operationSign.equals(MINUS)){
            resultOfOperation = subtractionNumbers(operandTwo, operandOne);
        }
        if(operationSign.equals(DIVIDE)){
            resultOfOperation = divideNumbers(operandTwo, operandOne);
        }
        if(operationSign.equals(MULTIPLY)){
            resultOfOperation = multiplyNumbers(operandTwo, operandOne);
        }
       return Optional.of(resultOfOperation);
    }

    @Override
    public Deque<String> mapInfixNotationToPostfixNotation(String expression) {
        Deque<String> queue = new ArrayDeque<>();
        Stack<String> stack = new Stack<>();
        Boolean lastSymbolInQueueIsNumber = false;
        char[] sequence = expression.replaceAll(" ","").toCharArray();

        for (int i = 0; i < sequence.length; i++) {

            if (sequence[i] == '(') {
                stack.push(LEFT_BRACKET);
                lastSymbolInQueueIsNumber = false;
                continue;
            }

            if (sequence[i] == ')') {
                while (!stack.peek().equals(LEFT_BRACKET)) {
                    queue.add(stack.pop());
                }
                stack.pop();
                lastSymbolInQueueIsNumber = false;
                continue;
            }

            if (String.valueOf(sequence[i]).matches(REGEX_NUMBER)) {
                if(lastSymbolInQueueIsNumber){
                    queue.add(queue.pollLast() + sequence[i]);
                    continue;
                }
                queue.add(String.valueOf(sequence[i]));
                lastSymbolInQueueIsNumber = true;
                continue;
            }

            if (String.valueOf(sequence[i]).matches(REGEX_OPERATION_SINGS)) {

                if (stack.isEmpty() || stack.peek().equals(LEFT_BRACKET)) {
                    stack.push(String.valueOf(sequence[i]));
                    lastSymbolInQueueIsNumber = false;
                    continue;
                }

                //Если входящий оператор имеет более высокий приоритет чем вершина стека, помещаем его в стек.
                if(sequence[i]=='*'||sequence[i]=='/'){

                    if(stack.peek().equals(PLUS)||stack.peek().equals(MINUS)){
                        stack.push(String.valueOf(sequence[i]));
                        lastSymbolInQueueIsNumber = false;
                        continue;
                    }

                    //Если входящий оператор имеет более низкий или равный приоритет, чем на вершине стека, выгружаем стек в очередь,
                    //пока не увидим оператор с меньшим приоритетом или левую скобку на вершине стека, затем добавляем входящий оператор в стек.
                    if(stack.peek().equals(MULTIPLY)||stack.peek().equals(DIVIDE)){
                        while (true){
                            if(stack.empty()||stack.peek().equals(MINUS)||stack.peek().equals(PLUS)||stack.peek().equals(LEFT_BRACKET)){
                                break;
                            }
                            queue.add(stack.pop());
                        }
                        stack.push(String.valueOf(sequence[i]));
                        lastSymbolInQueueIsNumber = false;
                        continue;
                    }
                }

                //Если входящий оператор имеет более низкий или равный приоритет, чем на вершине стека, выгружаем POP в очередь ,
                //пока не увидим оператор с меньшим приоритетом или левую скобку на вершине стека, затем добавляем входящий оператор в стек.
                if(sequence[i]=='-'||sequence[i]=='+'){
                    for (int j = stack.size()-1; j >= 0; j--) {
                        if(stack.peek().equals(LEFT_BRACKET)){
                            break;
                        }
                        queue.add(stack.pop());
                    }
                    stack.push(String.valueOf(sequence[i]));
                    lastSymbolInQueueIsNumber = false;
                }
            }
        }

        for (int i = 0; i <= stack.size(); i++) {
            queue.add(stack.pop());
        }
        return queue;
    }

    @Override
    public void checkCountBrackets(String expression) {
        Matcher matcherOpenBracket =  Pattern.compile("\\(").matcher(expression);
        Matcher matcherCloseBracket =  Pattern.compile("\\)").matcher(expression);
        int countOpenBrackets = 0;
        int countCloseBrackets = 0;
        while (matcherOpenBracket.find()){
            countOpenBrackets++;
        }
        while (matcherCloseBracket.find()){
            countCloseBrackets++;
        }
        if(countCloseBrackets!=countOpenBrackets){
            throw new IllegalArgumentException("Несовпадение количества скобок!");
        }
    }

    @Override
    public void checkExpressionContainsLetters(String expression){
        Matcher matcher =  Pattern.compile(REGEX_LETTERS).matcher(expression);
        if(matcher.find()){
            throw new IllegalArgumentException("Выражение не должно содержать буквы!");
        }
    }

    @Override
    public Double additionNumbers(Double operandOne , Double operandTwo){
        return operandOne + operandTwo;
    }

    @Override
    public Double subtractionNumbers(Double operandOne , Double operandTwo){
        return operandOne - operandTwo;
    }

    @Override
    public Double divideNumbers(Double operandOne , Double operandTwo) {
        if(operandTwo == 0){
            throw new IllegalArgumentException("на ноль делить нельзя");
        }
        return operandOne / operandTwo;
    }

    @Override
    public Double multiplyNumbers(Double operandOne , Double operandTwo){
        return  operandOne * operandTwo;
    }


}
