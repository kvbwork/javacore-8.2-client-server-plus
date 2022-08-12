package kvbdev;

import java.util.concurrent.CompletableFuture;

public class Launcher {

    public static void main(String[] args) {

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> ServerExample.main(null)),
                CompletableFuture.runAsync(() -> ClientExample.main(null))
        ).join();

        System.exit(0);
    }

}
