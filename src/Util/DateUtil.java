package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

    public static Date parseDate(String oldDate) {
        try {
            return dateFormat.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDiffBetween(Date date1, Date date2) {
        long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
        return (int) diff;
    }

    public static int getDiffBetween(String date1, String date2) {
        return getDiffBetween(parseDate(date1), parseDate(date2));
    }
}
