package org.example;

import lombok.Getter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Getter
public class SocketWrapper implements AutoCloseable {

    private final long clientId;
    private final Socket connection;
    private final Scanner input;

    private final PrintWriter output;
    private final boolean admin;

    public SocketWrapper(long clientId, Socket connection) throws IOException {
        this.clientId = clientId;
        this.connection = connection;
        this.input = new Scanner(connection.getInputStream());
        this.output = new PrintWriter(connection.getOutputStream(), true);
        this.admin = checkIfAdmin(); // проверяем, является ли клиент администратором
    }

    private boolean checkIfAdmin() {
        // проверка, является ли текущий клиент администратором
        // логика проверки здесь
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
