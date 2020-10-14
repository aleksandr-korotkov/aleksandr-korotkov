import services.CalcService;
import services.CalcServiceImpl;
import services.ConsoleService;
import services.ConsoleServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CalcService calcService = new CalcServiceImpl();
        ConsoleService consoleService = new ConsoleServiceImpl();

        String expression = consoleService.readExpression().get();
        consoleService.writeResultOfOperation(calcService.calcExpression(expression));

    }
}
