package tahainshad.com.app.control;

import java.util.Locale;

/**
 * Created by Saeed on 21/03/2017.
 */

public final class MyHelper {
    /* نعطية رقم بالميلي ثانية يرجع الزمن بالشكل mm:ss */
    public static String getTimeString(long duration) {
        int minutes = (int) Math.floor(duration / 1000 / 60);
        int seconds = (int) ((duration / 1000) - (minutes * 60));
        return String.format(Locale.US, "%02d", minutes) + ":" + String.format(Locale.US, "%02d", seconds);
    }
}
