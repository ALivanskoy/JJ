package org.example;

import org.example.SocketWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {

    public static final int PORT = 8181;

    private static long clientIdCounter = 1L;
    private static Map<Long, SocketWrapper> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);
            while (true) {
                final Socket client = server.accept();
                final long clientId = clientIdCounter++;

                SocketWrapper wrapper = new SocketWrapper(clientId, client);
                System.out.println("Подключился новый клиент[" + wrapper.getClientId() + "]");
                clients.put(clientId, wrapper);

                new Thread(new ClientHandler(wrapper)).start();
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private SocketWrapper wrapper;

        public ClientHandler(SocketWrapper wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public void run() {
            try (Scanner input = wrapper.getInput(); PrintWriter output = wrapper.getOutput()) {
                output.println("Подключение успешно. Список всех клиентов: " + clients);

                while (true) {
                    String clientInput = input.nextLine();
                    if (clientInput.startsWith("@")) {
                        // формат сообщения: "@4 сообщение" - отправить конкретному клиенту с id=4
                        int spaceIndex = clientInput.indexOf(' ');
                        if (spaceIndex != -1 && spaceIndex < clientInput.length() - 1) {
                            long destinationId = Long.parseLong(clientInput.substring(1, spaceIndex));
                            String message = clientInput.substring(spaceIndex + 1);
                            SocketWrapper destination = clients.get(destinationId);
                            if (destination != null) {
                                destination.getOutput().println("Сообщение от клиента[" + wrapper.getClientId() + "]: " + message);
                            } else {
                                output.println("Клиент с id=" + destinationId + " не найден");
                            }
                        }
                    } else if (clientInput.startsWith("kick") && wrapper.isAdmin()) {
                        // формат сообщения: "kick 4"
                        long clientIdToKick = Long.parseLong(clientInput.substring(5));
                        SocketWrapper clientToKick = clients.get(clientIdToKick);
                        if (clientToKick != null) {
                            clientToKick.getConnection().close();
                            clients.remove(clientIdToKick);
                            System.out.println("Клиент[" + clientIdToKick + "] был отключен администратором");
                        } else {
                            output.println("Клиент с id=" + clientIdToKick + " не найден");
                        }
                    } else {
                        // отправить сообщение всем клиентам, кроме отправителя
                        clients.values().stream()
                                .filter(it -> it.getClientId() != wrapper.getClientId())

                                .forEach(it -> it.getOutput().println("Сообщение от клиента[" + wrapper.getClientId() + "]: " + clientInput));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
