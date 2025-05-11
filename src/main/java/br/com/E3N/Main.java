package br.com.E3N;

import br.com.E3N.comparing.*;
import br.com.E3N.factory.CompareImageFactory;

import java.io.File;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        SameImage sameImage = SameImage.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!sameImage.getFinished()){
                System.out.println("\n[Shutdown] Ending program...");
                System.out.println(sameImage.toString());
                System.out.println("Comparison: " + sameImage.getComparisonDone());
            }
        }));
        String path = null;
        String path1 = null;
        Integer start = null;
        Integer end = null;
        File[] files1 = null;
        File[] files2 = null;

        if (args.length == 3){
            path = args[1];
            path1 = args[2];
            files1 = GetFiles.getFiles(path);
            files2 = GetFiles.getFiles(path1);
        } else {
            path = args[1];
            path1 = args[2];
            start = Integer.parseInt(args[3]);
            end = Integer.parseInt(args[4]);
            files1 = GetFiles.getFiles(start, end, path);
            files2 = GetFiles.getFiles(path1);
        }

        CompareImage compareImage = CompareImageFactory.compareCreator(args[0]);
        MultipleExecutorThread executorThread = new MultipleExecutorThread(files1, files2, compareImage);
        executorThread.execute();
        if(sameImage.getFinished()){
            System.out.println(sameImage.toString());
            System.out.println("Comparison: " + sameImage.getComparisonDone());
        }
    }
}