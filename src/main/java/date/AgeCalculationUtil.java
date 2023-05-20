package date;

import console.OutputMassage;

import java.time.LocalDate;

public class AgeCalculationUtil {
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
            OutputMassage massage = new OutputMassage();

            massage.ageIsNegative();
        }

        if (age > 115) {
            OutputMassage massage = new OutputMassage();

            massage.impossibleAge(age);
        }

        return age;
    }
}
