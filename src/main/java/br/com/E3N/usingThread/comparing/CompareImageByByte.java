package br.com.E3N.usingThread.comparing;

import br.com.E3N.shared.SameImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CompareImageByByte implements CompareImage {

    @Override
    public Runnable imageCompare(final File file1, final File[] files) throws IOException {
        return () -> {
            try {
                SameImage sameImage = SameImage.getInstance();
                for (File file : files) {
                    if (file1.getName().equals(file.getName())) {
                        continue;
                    }
                    byte[] imageBytes = Files.readAllBytes(Path.of(file1.getPath()));
                    byte[] imageBytes2 = Files.readAllBytes(Path.of(file.getPath()));
                    boolean isEqual = Arrays.equals(imageBytes2, imageBytes);
                    if (isEqual) {
//                        if (sameImage.doNotHave(file.getName()))
                            sameImage.add(file1.getName(), file.getName());
                        sameImage.comparisonCount();
                    }
                }
                sameImage.hasFinished();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        };
    }
}
