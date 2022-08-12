package kvbdev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class VerboseUserInteraction {

    private final String name;
    private final BufferedReader in;
    private final PrintWriter out;

    public VerboseUserInteraction(String name, BufferedReader in, PrintWriter out) {
        this.name = name;
        this.in = in;
        this.out = out;
    }

    public void send(String text){
        System.out.println(name + " send: " + text);
        out.println(text);
    }

    public String receive() throws IOException {
        String text = in.readLine();
        System.out.println(name + " received: " + text);
        return text;
    }
}
