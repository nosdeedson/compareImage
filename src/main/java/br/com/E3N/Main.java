package br.com.E3N;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        SameImage sameImage = SameImage.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n[Shutdown] Ending program...");
            System.out.println(sameImage.toString());
            System.out.println("Comparison: " + sameImage.getComparisonDone());
        }));
        MultipleExecutorThread executorThread = new MultipleExecutorThread();
        String path = args[0];
        Integer start = null;
        Integer end = null;
        if (args.length == 3) {
            start = Integer.parseInt(args[1]);
            end = Integer.parseInt(args[2]);

        }
        System.out.println(path);
        executorThread.execute(path, start, end);
        System.out.println(sameImage.toString());
        System.out.println("Comparison: " + sameImage.getComparisonDone());
    }
}