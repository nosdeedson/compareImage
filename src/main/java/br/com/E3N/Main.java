package br.com.E3N;

import br.com.E3N.ActorModel.ActorApp;
import br.com.E3N.usingThread.App;

import java.io.IOException;
import java.time.Instant;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        long begging = Instant.now().toEpochMilli();
        try {
            if ("byByte".equalsIgnoreCase(args[0]) || "byPixel".equalsIgnoreCase(args[0])) {
                App.execute(args);
            } else if ("actor".equalsIgnoreCase(args[3])) {
                ActorApp.execute(args);
            } else {
                System.out.println("DOING ANYTHING");
            }

        } catch (Exception error) {
            System.err.println("💥 Out of memory! Stopping program.");
            System.exit(1); // force program exit with error code
        }
        long ending = Instant.now().toEpochMilli();
        long result = ending - begging;
        double seconds = result / 1000.0;
        System.out.println("Time spent: " + seconds + " seconds.");
    }

}