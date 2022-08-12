package kvbdev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientExample {

    public static void main(String[] args) {

        String host = "netology.homework";
        int port = 8080;

        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.printf("client: Established connection with %s:%d%n",
                    socket.getInetAddress(), socket.getPort());

            VerboseUserInteraction me = new VerboseUserInteraction("CLIENT", in, out);

            me.send("hello");
            me.receive();

            me.receive();
            me.send("CLIENT_NAME");

            me.receive();
            me.send("yes");

            me.receive();
            me.send("cool");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
