package br.com.E3N.comparing;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultipleExecutorThread {

    private final int quantityOfThread = 4;
    private final CompareImage compareImage;
    private final File[] files1;
    private final File[] files2;
    private final ExecutorService executorService = Executors.newFixedThreadPool(quantityOfThread);

    public MultipleExecutorThread(final File[] files1, final File[] files2, CompareImage compareImage){
        this.files1 = files1;
        this.files2 = files2;
        this.compareImage = compareImage;
    }

    public void execute() throws InterruptedException, IOException {

        for (int i = 0; i < files1.length; i++) {
            this.executorService.submit(compareImage.imageCompare(files1[i], files2));
        }
        System.out.println("############## END FOR CREATE THREADS #########################");
        this.executorService.shutdown();
        this.executorService.awaitTermination(1, TimeUnit.HOURS);
    }

}
