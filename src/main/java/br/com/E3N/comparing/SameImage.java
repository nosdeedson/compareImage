package br.com.E3N.comparing;

import java.util.HashMap;
import java.util.Map;

public final class SameImage {

    public static SameImage sameImage;
    private final Map<String, String> same;
    private int comparisonDone = 0;
    private boolean finished = false;

    private SameImage() {
        this.same = new HashMap<>();
    }

    public static SameImage getInstance() {
        if (sameImage == null) {
            sameImage = new SameImage();
        }
        return sameImage;
    }

    public synchronized void comparisonCount() {
        comparisonDone++;
    }

    public synchronized void add(final String key, final String value) {
        this.same.put(key, value);
    }

    public boolean doNotHave(final String key) {
        return !same.containsKey(key);
    }

    public void hasFinished(){
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
        for (Map.Entry<String, String> entry : same.entrySet()) {
            builder.append(entry.getKey());
            builder.append(" : ");
            builder.append(entry.getValue());
            builder.append("\n");
        }
        builder.append(same.size());
        return builder.toString();
    }
}
