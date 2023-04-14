package organization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckEnteredDateTest {
    @Test
    @DisplayName("The date entered exactly")
    public void check_enteredRight_success() {
//        given
        String date = "1957-12-01";
        boolean expected = true;


//        when
        boolean actual = CheckEnteredDate.check(date);


//        then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("The date entered wrong")
    public void check_enteredFalse_exception() {
//        given
        String date = "1957-2-01";


//        then
        assertThrows(IllegalArgumentException.class, () -> CheckEnteredDate.check(date));
    }
}