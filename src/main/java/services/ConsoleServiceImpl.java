package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ConsoleServiceImpl implements ConsoleService {
    @Override
    public Optional<String> readExpression() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите выражение");
        try {
            return Optional.ofNullable(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
        return Optional.empty();
    }

    @Override
    public void writeResultOfOperation(String result) {
        System.out.println("Результат вычисления выражения: " + result);
    }
}
