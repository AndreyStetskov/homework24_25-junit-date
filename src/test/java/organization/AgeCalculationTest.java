package organization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @DisplayName("Got in with different values")
//    given
    @CsvSource({"2023/04/20, 1999/04/11, 23",
            "2023/04/20, 1999/04/20, 24",
            "2023/04/20, 1999/10/20, 24",
            "2023/04/20, 1999/01/11, 24",
    })


//    when
    public void ageCalculation_equalMonthDifferentDay_success(@ConvertWith(SimpleArgumentConverter.class) LocalDate today, LocalDate birthday, int expected) {

    }

//    then

}