package date.control;

import console.OutputMassage;
import date.auxiliary.Month;

import java.time.LocalDate;

public class CheckEnteredDate {

    private final OutputMassage massage;
    private Month monthOfYear;

    public CheckEnteredDate() {
        massage = new OutputMassage();
    }

    public boolean isDateForLocalDate(String date) {

        if (isDateWithInvalidCharacter(date)) return false;

        if (!isDateCorrectly(date)) return false;

        this.checkForIncompleteInput(date);

        try {
            LocalDate birthdate = LocalDate.parse(date);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Access denied - You have to enter your birthday correctly in the format YYYY-MM-DD");
        }

        return true;
    }

    private boolean isDateWithInvalidCharacter(String date) {
        if (date.equals("") || date == null) {

            massage.dateIsNull();

            return true;
        }

        if (!date.matches("[0-9-]*$")) {

            massage.dateConsistExtraCharacter();
            
            return true;
        }

        String [] divideDate = date.split("-");

        if (divideDate.length != 3) {
            massage.dateIsIncorrectly();

            if (divideDate.length > 3) massage.dateConsistSoManySeparators();
            else  massage.dateConsistSoFewSeparators();

            return true;
        }

        return false;
    }

    private boolean isDateCorrectly(String date){
        int countError = 0;
        String [] divideDate = date.split("-");

        int year = Integer.parseInt(divideDate[0]);
        int month = Integer.parseInt(divideDate[1]);
        int day = Integer.parseInt(divideDate[2]);

        int dayInMonth = Month.dayInMonth(month, year);

        if (divideDate[0].length() != 4 || month > 12 || day > dayInMonth) countError ++;

        if (countError != 0) {

            massage.dateWithIncorrectValue(date);

            return false;
        }
        else return true;
    }

    public String checkForIncompleteInput(String date) {

        String [] divideDate = date.split("-");

        if (divideDate[1].length() == 1) date = date.substring(0, 5) + "0" + date.substring(5);
        if (divideDate[2].length() == 1) date = date.substring(0, 8) + "0" + date.substring(8);

        return date;
    }
}
