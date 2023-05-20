package console;

import date.auxiliary.Month;
import entity.organization.educational.EducationalDTO;
import entity.organization.educational.Type;

import java.util.List;

public class OutputMassage {

    public void beforeEnteredBirthday() {
        System.out.println("Please, enter your birthday in the format YYYY-MM-DD, for example 1990-01-03");
    }

    public void accessGranted() {
        System.out.println("Access granted\n");
    }

    public void accessDenied() {
        System.out.println("Access  denied");
        System.out.println("Try again and see example carefully please\n");
    }

    public void dateIsNull() {
        System.err.println("You've entered nothing. The date should consist of numbers and \"-\" between numbers.\n");
    }

    public void dateConsistExtraCharacter() {
        System.err.println("You've entered extra characters. The date should consist of numbers and \"-\" between numbers.\n");
    }

    public void dateIsIncorrectly() {
        System.err.println("The date was entered incorrectly.");
    }

    public void dateConsistSoManySeparators() {
        System.err.println("You entered so many separators.\n");
    }

    public void dateConsistSoFewSeparators() {
        System.err.println("You entered so few separators.\n");
    }

    public void dateWithIncorrectValue(String date) {

        String[] divideDate = date.split("-");

        int year = Integer.parseInt(divideDate[0]);
        int month = Integer.parseInt(divideDate[1]);
        int day = Integer.parseInt(divideDate[2]);

        if (divideDate[0].length() != 4)
            System.err.println("You entered the wrong year.");

        if (divideDate[0].length() < 4)
                System.err.println("Are you really more than a thousand years young?? It's incredible.\n");

        if (divideDate[0].length() > 4)
                System.err.println("Perhaps you'll be born a thousand years from now. But let's keep the fiction out of it.\n");

        if (month > 12)
            System.err.println("There cannot be more than 12 month in a year.\n");

        int dayInMonth = Month.dayInMonth(month, year);

        if (day > dayInMonth) {
            System.err.println(this.messageAboutError(month, year));
        }
    }


    private String messageAboutError(int month, int year) {
        String message = "";
        Month monthOfYear = null;

        for (Month m : Month.values()) {
            if (m.ordinal() + 1 == month) {
                monthOfYear = m;
            }
        }

        if (Month.isLeap(year) && monthOfYear == Month.FEBRUARY) message += "February cannot be more than 29 days in leap year.\n";
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

        return message;
    }


    public void ageIsNegative() {
        System.out.println("Your date of birth is wrong. You weren't born yet");
        System.out.println("Try again\n");
    }

    public void bigAge(int age) {
        System.out.printf("Are you definitely %d years old?! You're in great shape!)\n", age);
    }

    public void littleAge(int age) {
        System.out.printf("Your age is %s years young\n", age);
    }

    public void impossibleAge(int age) {
        System.out.printf("%s is really impossible age. You entered wrong year\n", age);
    }

    public void recommendedPreSchool() {
        System.out.printf("%s recommended for you\n\n", Type.PRE_SCHOOL.getSchool());
    }

    public void recommendedElementarySchool() {
        System.out.printf("%s recommended for you\n\n", Type.ELEMENTARY_SCHOOL.getSchool());
    }

    public void recommendedMiddleSchool() {
        System.out.printf("%s recommended for you\n\n", Type.MIDDLE_SCHOOL.getSchool());
    }

    public void recommendedHighSchool() {
        System.out.printf("%s recommended for you\n\n", Type.HIGH_SCHOOL.getSchool());
    }

    public void recommendedUniversity() {
        System.out.printf("%s recommended for you\n\n", Type.UNIVERSITY.getSchool());
    }

    public void notRecommendedToStudy() {
        System.out.println("You're not recommended to study");
    }

    public void beforeListOfInstructions() {
        System.out.println("The following educational institutions would be suitable for you:");
    }

    public void getInstructions(List<?> list) {
        if (list.size() >= 1) printInstruction(list);
        else System.out.println("Unfortunately, your age isn't suitable for any school");
    }

    private void printInstruction(List<?> list) {
        list.forEach(o -> System.out.println(o.toString()));
    }

    public void getInstruction(EducationalDTO educationalDTO) {
        System.out.println(educationalDTO);
    }
}
