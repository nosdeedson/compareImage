package br.com.E3N.usingThread.comparing;

import java.io.File;
import java.io.IOException;

public interface CompareImage {

    public Runnable imageCompare(final File file1, final File[] files) throws IOException;

}
