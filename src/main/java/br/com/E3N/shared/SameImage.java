package br.com.E3N.shared;

import java.util.HashSet;
import java.util.Set;

public final class SameImage {

    private static SameImage sameImage;


    private final Set<Pair> same = new HashSet<>();
    private int comparisonDone = 0;
    private boolean finished = false;

    private SameImage() {
    }

    public static synchronized SameImage getInstance() {
        if (sameImage == null) {
            sameImage = new SameImage();
        }
        return sameImage;
    }

    public synchronized void comparisonCount() {
        comparisonDone++;
    }

    public synchronized void add(final String key, final String value) {
        same.add(new Pair(key, value));
    }

    public boolean doNotHave(final String key) {
        return false;
    }

    public void hasFinished() {
        this.finished = true;
    }

    public int getComparisonDone() {
        return comparisonDone;
    }

    public boolean getFinished() {
        return this.finished;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Pair entry : same) {
            builder.append(entry.key)
                    .append(" : ")
                    .append(entry.value)
                    .append("\n");
        }
        builder.append("comparison: ")
                .append(comparisonDone)
                .append("\n");
        return builder.toString();
    }

    private record Pair(String key, String value) {
        private Pair(String key, String value) {
            if (key.compareTo(value) <= 0) {
                this.key = key;
                this.value = value;
            } else {
                this.key = value;
                this.value = key;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair(String key1, String value1))) return false;
            return key.equals(key1) && value.equals(value1);
        }

    }
}
