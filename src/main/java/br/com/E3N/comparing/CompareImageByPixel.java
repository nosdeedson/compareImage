package br.com.E3N.comparing;

import dev.brachtendorf.jimagehash.hash.Hash;
import dev.brachtendorf.jimagehash.hashAlgorithms.HashingAlgorithm;
import dev.brachtendorf.jimagehash.hashAlgorithms.PerceptiveHash;

import java.io.File;
import java.io.IOException;

public class CompareImageByPixel implements CompareImage {

    @Override
    public Runnable imageCompare(File file1, File[] files) throws IOException {
        return () -> {
            try {
                SameImage sameImage = SameImage.getInstance();
                for (File file : files) {
                    if (file1.getName().equals(file.getName())) {
                        continue;
                    }
                    HashingAlgorithm hasher = new PerceptiveHash(32);

                    Hash hash0 = hasher.hash(file1);
                    Hash hash1 = hasher.hash(file);

                    double similarityScore = hash0.normalizedHammingDistance(hash1);

                    if (similarityScore < .2) {
                        if (sameImage.doNotHave(file.getName())){
                            sameImage.add(file1.getName(), file.getName());
                            sameImage.comparisonCount();
                        }
                    }
                }
                sameImage.hasFinished();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}