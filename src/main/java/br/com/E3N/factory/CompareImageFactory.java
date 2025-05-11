package br.com.E3N.factory;

import br.com.E3N.comparing.CompareImage;
import br.com.E3N.comparing.CompareImageByByte;
import br.com.E3N.comparing.CompareImageByPixel;

public final class CompareImageFactory {

    public static CompareImage compareCreator(final String type) {
        if (type.equals("byByte")){
            return new CompareImageByByte();
        }
        return new CompareImageByPixel();
    }
}
