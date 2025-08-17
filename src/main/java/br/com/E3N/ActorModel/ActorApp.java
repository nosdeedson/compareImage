package br.com.E3N.ActorModel;

import br.com.E3N.shared.GetFiles;
import br.com.E3N.shared.SameImage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ActorApp {

    public static void execute(String[] args) throws InterruptedException {
        SameImage sameImage = SameImage.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!sameImage.getFinished()){
                System.out.println("\n[Shutdown] Ending program...");
                System.out.println(sameImage.toString());
                System.out.println("Comparison: " + sameImage.getComparisonDone());
            }
        }));
        ActorSystem actorSystem = new ActorSystem(Integer.parseInt(args[0]));
        ActorCoordinator coordinator = new ActorCoordinator("Coordinator");
        actorSystem.actorOf(coordinator);

        ActorImage actorImage = new ActorImage("image");
        actorSystem.actorOf(actorImage);
        File[] files1 = GetFiles.getFiles(args[1]);
        File[] files2 = GetFiles.getFiles(args[2]);
//        List<File> list1 = new ArrayList<>(Arrays.asList(files1));
//        List<File> list2 = new ArrayList<>(Arrays.asList(files2));
        for (File file : files1) {
            for (File value : files2) {
                if (file.getName().equalsIgnoreCase(value.getName())) {
                    continue;
                }
                coordinator.sendTo(actorImage, file, value, "compare");
            }
        }
        int sleepTime = Integer.parseInt(args[4]);
        Thread.sleep(sleepTime);

        coordinator.sendTo(coordinator, null, null, "stop");

        Thread.sleep(1_000);
        actorSystem.shutdown();
        System.out.println("ActorSystem shutdown completely.");
        System.out.println(sameImage.toString());
    }

}
