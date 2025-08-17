package br.com.E3N.shared;

import java.io.File;

public final class GetFiles {

    public static File[] getFiles(final Integer start, final Integer end, final String path){
        if(start != null){
            File[] files = new File[end];
            for (int i = start; i < end; i++) {
                String pathOfFile = path + i;
                files[i] = new File(pathOfFile);
            }
            return  files;
        } else {
            File folder = new File(path);
            return folder.listFiles();
        }
    }

    public static File[] getFiles(final String path){
        File folder = new File(path);
        return folder.listFiles(File::isFile);
    }

}
