package organization;

import java.time.LocalDate;

public class CheckEnteredData {
    public static boolean isDateForLocalDate(String date) {
        if (date == null) {
            System.out.println("You've entered nothing. The date should consist of numbers and \"-\" between numbers.\n");

            return false;
        }

        if (date.equals("")) {
            System.out.println("You've entered nothing. The date should consist of numbers and \"-\" between numbers.\n");

            return false;
        }

        if (!date.matches("[0-9-]*$")) {
            System.out.println("You've entered extra characters. The date should consist of numbers and \"-\" between numbers.\n");

            return false;
        }

        if (new CheckEnteredData().isDateCorrectly(date)) {
            String [] divideDate = date.split("-");

            if (divideDate[1].length() == 1) date = date.substring(0, 5) + "0" + date.substring(5);
            if (divideDate[2].length() == 1) date = date.substring(0, 8) + "0" + date.substring(8);

            try {
                LocalDate birthdate = LocalDate.parse(date);

                return true;
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Access denied - You have to enter your birthday correctly in the format YYYY-MM-DD");
            }
        }
        else return false;
    }

    private boolean isDateCorrectly(String date){
        int countError = 0;
        String [] divideDate = date.split("-");

        if (divideDate.length != 3) {
            System.out.println("You entered the date incorrectly.");

            if (divideDate.length > 3) System.out.println("You entered so many separators.\n");
            else  System.out.println("You entered so few separators.\n");

            return false;
        }

        int year = Integer.parseInt(divideDate[0]);
        int month = Integer.parseInt(divideDate[1]);
        int day = Integer.parseInt(divideDate[2]);

        if (divideDate[0].length() != 4) {
            System.out.println("You entered the wrong year.");
            countError ++;

            if (divideDate[0].length() < 4) System.out.println("Are you really more than a thousand years young?? It's incredible.\n");
            else if (divideDate[0].length() > 4) System.out.println("Perhaps you'll be born a thousand years from now. But let's keep the fiction out of it.\n");
            else System.out.println("The year can't be negative.\n");
        }


        if (divideDate[1].length() > 2) {
            System.out.println("You entered the wrong month. It must contain two digits. " +
                    "If your month of birth is a single-digit number, please enter 0 in front of it\n");
            countError ++;
        }

        if (month > 12) {
            System.out.println("There cannot be more than 12 month in a year.\n");
            countError ++;
        }


        if (divideDate[2].length() > 2) {
            System.out.println("You entered the wrong day. It must contain two digits. " +
                    "If your day of birth is less than 10, enter 0 in front of it\n");
            countError ++;
        }

        if (divideDate[1].length() == 1 || divideDate[2].length() == 1) return true;

        int dayInMonth = dayInMonth(month, year);

        if (day > dayInMonth) {
            System.out.println(new CheckEnteredData().messageAboutError(month, year));
            countError ++;
        }


        if (countError != 0) return false;
        else return true;
    }

    private static boolean isLeap(int year) {
        if (year % 4 == 0 && year != 100 || year % 400 == 0) return true;
        else return false;
    }

    private static int dayInMonth(int month, int year) {
        int days = 0;
        Month monthOfYear = null;

        for (Month m : Month.values()) {
            if (m.ordinal() + 1 == month) {
                monthOfYear = m;
            }
        }

        if (monthOfYear != null) {
            switch (monthOfYear) {
                case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> days = 31;
                case APRIL, JUNE, SEPTEMBER, NOVEMBER -> days = 30;
                case FEBRUARY -> days = 28;
            }
        }

        if (isLeap(year) && monthOfYear == Month.FEBRUARY) days ++;

        return days;
    }

    private String messageAboutError(int month, int year) {
        String message = "";
        Month monthOfYear = null;

        for (Month m : Month.values()) {
            if (m.ordinal() + 1 == month) {
                monthOfYear = m;
            }
        }

        if (monthOfYear != null) {
            if (isLeap(year) && monthOfYear == Month.FEBRUARY) message += "February cannot be more than 29 days in leap year.\n";
            else switch (monthOfYear) {
                case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> message = message +
                        monthOfYear.toString().charAt(0) +
                        monthOfYear.toString().substring(1).toLowerCase() +
                        " cannot be more than 31 days.\n";

                case APRIL, JUNE, SEPTEMBER, NOVEMBER -> message = message +
                        monthOfYear.toString().charAt(0) +
                        monthOfYear.toString().substring(1).toLowerCase() +
                        " cannot be more than 30 days.\n";

                case FEBRUARY -> message += "February cannot be more than 28 days.\n";
            }
        }

        return message;
    }
}
