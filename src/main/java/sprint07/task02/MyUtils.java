package sprint07.task02;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class MyUtils {
    public static String getDateAfterToday(int years, int months, int days) {
        LocalDate current = LocalDate.now();
        return current.plusYears(years).plusMonths(months).plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}