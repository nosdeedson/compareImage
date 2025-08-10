package br.com.E3N.usingThread.factory;

import br.com.E3N.usingThread.comparing.CompareImage;
import br.com.E3N.usingThread.comparing.CompareImageByByte;
import br.com.E3N.usingThread.comparing.CompareImageByPixel;

public final class CompareImageFactory {

    public static CompareImage compareCreator(final String type) {
        if (type.equals("byByte")){
            return new CompareImageByByte();
        }
        return new CompareImageByPixel();
    }
}
