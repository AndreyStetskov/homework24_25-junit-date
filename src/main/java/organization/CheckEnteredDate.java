package organization;

import java.time.LocalDate;

public class CheckEnteredDate {
    public static boolean check(String date) {
        try {
            LocalDate birthdate = LocalDate.parse(date);
            return true;
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Access denied - You have to enter your birthday correctly in the format YYYY-MM-DD");
        }
    }
}
