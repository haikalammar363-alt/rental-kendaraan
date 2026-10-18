package view;

import java.util.Locale;

public class Format {
    public static String formatRupiah(long nilai) {
        return String.format(Locale.US, "Rp%,d", nilai).replace(',', '.');
    }
}
