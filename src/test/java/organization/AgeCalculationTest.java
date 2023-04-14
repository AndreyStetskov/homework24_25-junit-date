package organization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AgeCalculationTest {
    @Test
    @DisplayName("Got in with different values")
    public void ageCalculation_differentValues_success() {
//        given
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1999, Month.AUGUST,11);
        int expected = 23;

        LocalDate birthdaySecond = LocalDate.of(1999, Month.JANUARY,15);
        int expectedSecond = 24;


//        when
        int actual = AgeCalculation.ageCalculation(today, birthday);
        int actualSecond = AgeCalculation.ageCalculation(today, birthdaySecond);


//        then
        assertEquals(expected, actual);
        assertEquals(expectedSecond, actualSecond);
    }

    @Test
    @DisplayName("Got in with equal month and different day")
    public void ageCalculation_equalMonthDifferentDay_success() {
//        given
        LocalDate today = LocalDate.now();

        LocalDate birthday = LocalDate.of(1999, Month.APRIL,11);
        int expected = 23;

        LocalDate birthdaySecond = LocalDate.of(1999, Month.APRIL,15);
        int expectedSecond = 24;


//        when
        int actual = AgeCalculation.ageCalculation(today, birthday);
        int actualSecond = AgeCalculation.ageCalculation(today, birthdaySecond);


//        then
        assertEquals(expected, actual);
        assertEquals(expectedSecond, actualSecond);
    }
}