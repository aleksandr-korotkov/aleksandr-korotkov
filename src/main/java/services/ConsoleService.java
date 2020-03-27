package services;

import java.io.IOException;
import java.util.Optional;

public interface ConsoleService {

    Optional<String> readExpression() throws IOException;

    void writeResultOfOperation(String result);
}
