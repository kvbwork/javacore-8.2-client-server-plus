package kvbdev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {

    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("server: New connection accepted from %s:%d%n",
                            clientSocket.getRemoteSocketAddress(), clientSocket.getPort());

                    VerboseUserInteraction me = new VerboseUserInteraction("SERVER", in, out);

                    me.receive();
                    me.send("hello");

                    me.send("Write your name");
                    String nameAnswer = me.receive();

                    me.send("Are you child? (yes/no)");
                    String childAnswer = me.receive().toLowerCase();

                    if ("yes".equals(childAnswer)) {
                        me.send(String.format("Welcome to the kids area, %s! Let's play!", nameAnswer));
                    } else if ("no".equals(childAnswer)) {
                        me.send(String.format(
                                "Welcome to the adult zone, %s! Have a good rest,or a good working day!",
                                nameAnswer));
                    }
                    me.receive();

                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.flush();
    }

}
