package kvbdev;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Launcher {

    public static void main(String[] args) {

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> ServerExample.main(null))
                        .completeOnTimeout(null, 10, TimeUnit.SECONDS),
                CompletableFuture.runAsync(() -> ClientExample.main(null))
        ).join();

        System.exit(0);
    }

}
