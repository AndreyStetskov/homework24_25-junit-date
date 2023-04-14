package organization;

import java.time.LocalDate;

public class AgeCalculation {
    public static int ageCalculation(LocalDate today, LocalDate birthdate) {
        int age = today.getYear() - birthdate.getYear();

        if (today.getMonth().equals(birthdate.getMonth())) {
            if (today.getDayOfMonth() > birthdate.getDayOfMonth()){
                age --;
            }
        }

        if (today.getMonthValue() < birthdate.getMonthValue()) {
            age --;
        }

        if (age < 0) {
            System.out.println("Your date of birth is wrong. You weren't born yet");
            System.out.println("Try again\n");
        }

        return age;
    }
}
