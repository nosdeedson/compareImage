package br.com.E3N;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultipleExecutorThread {

    private final int quantityOfThread = 40;

    private final ExecutorService executorService = Executors.newFixedThreadPool(quantityOfThread);

    public void execute(final String path, Integer start, Integer end) throws InterruptedException, IOException {
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (start == null)
            start = 0;
        if (end == null)
            end = Objects.requireNonNull(files).length;
        for (int i = start; i < end; i++) {
            ImageCompare ic = new ImageCompare();
            if (files != null)
                this.executorService.submit(ic.imageCompare(files[i], files));
        }
        System.out.println("############## END FOR CREATE THREADS #########################");
        this.executorService.shutdown();
        this.executorService.awaitTermination(1, TimeUnit.HOURS);
    }

}
