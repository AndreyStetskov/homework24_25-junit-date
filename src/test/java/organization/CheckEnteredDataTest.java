package organization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CheckEnteredDataTest {
    CheckEnteredData checkDate;
    @ParameterizedTest
    @DisplayName("The date entered exactly")
    @ValueSource(strings = {"1988-02-03",
            "1998-5-03",
            "1933-12-2",
            "1969-1-1"
    })
    void isDateForLocalDate_enteredRight_success(String date) {


//        then
        assertTrue(CheckEnteredData.isDateForLocalDate(date));
    }


    @ParameterizedTest
    @DisplayName("The date entered with various mistakes")
    @ValueSource(strings = {"YYYY-MM-DD",
            "1978-)5-03",
            "1931-02",
            "2002",
            " -0- ",
            "dhgjlkvjhlfdhg",
            "1999--02-01",
            "199-01-01",
            "30955-01-03",
            "2001-15-01",
            "1988-05-33",
            "1947-02-29",
            "2004-02-30",
            "25-11-1985"
    })
    @NullSource
    @EmptySource
    void isDateForLocalDate_enteredByMistake_success(String date) {


//        then
        assertTrue(CheckEnteredData.isDateForLocalDate(date));
    }


    @ParameterizedTest
    @DisplayName("The date entered with some symbols. Checking output")
    @ValueSource(strings = {"YYYY-MM-DD",
            "1978-)5-03",
            " -0- ",
            "dhgjlkvjhlfdhg"
    })
    void isDateForLocalDate_enteredByMistake_successOutputFirstMessage(String date) {
//        given
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "You've entered extra characters. The date should consist of numbers and \"-\" between numbers.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @ParameterizedTest
    @DisplayName("The date was entered short. Checking output")
    @ValueSource(strings = {"1931-02", "2002"})
    void isDateForLocalDate_enteredByMistake_successOutputSecondMessage(String date) {
//        given
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "You entered the date incorrectly.\r\n" +
                "You entered so few separators.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @ParameterizedTest
    @DisplayName("The date is null. Checking output")
    @NullSource
    @EmptySource
    void isDateForLocalDate_enteredByMistake_successOutputThirdMessage(String date) {
//        given
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "You've entered nothing. The date should consist of numbers and \"-\" between numbers.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date entered with a lot of separators. Checking output")
    void isDateForLocalDate_enteredByMistake_successOutputFourthMessage() {
//        given
        String date = "1957--12-01";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "You entered the date incorrectly.\r\n" +
                "You entered so many separators.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date have short year. Checking output")
    void isDateForLocalDate_enteredByMistake_successOutputFifthMessage() {
//        given
        String date = "957-12-01";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "You entered the wrong year.\r\n" +
                "Are you really more than a thousand years young?? It's incredible.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

           CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date have long year. Checking output")
    void isDateForLocalDate_enteredByMistake_successOutputSixthMessage() {
//        given
        String date = "11957-12-01";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "You entered the wrong year.\r\n" +
                "Perhaps you'll be born a thousand years from now. But let's keep the fiction out of it.\n\r\n";


        //        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }



    @Test
    @DisplayName("The date have long day in month no more than 30 day. Checking output")
    void isDateForLocalDate_enteredByMistake_successOutputSeventhMessage() {
//        given
        String date = "1947-06-41";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "June cannot be more than 30 days.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date have long day in month no more than 31 day. Checking output")
    void isDateForLocalDate_enteredByMistake_successOutputEighthMessage() {
//        given
        String date = "1929-07-33";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "July cannot be more than 31 days.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date have long day in February.")
    void isDateForLocalDate_enteredByMistake_successOutputNinthMessage() {
//        given
        String date = "1989-02-30";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "February cannot be more than 28 days.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date have long day in February in a leap year.")
    void isDateForLocalDate_enteredByMistake_successOutputTenthMessage() {
//        given
        String date = "2004-02-30";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "February cannot be more than 29 days in leap year.\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date contains a month that doesn't exist. Checking output")
    void isDateForLocalDate_enteredByMistake_successOutputEleventhMessage() {
//        given
        String date = "1949-22-30";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "There cannot be more than 12 month in a year.\n\r\n\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }


    @Test
    @DisplayName("The date entered with a few mistake. Checking output combine massages")
    void isDateForLocalDate_enteredByMistake_successOutputTwelveMessage() {
//        given
        String date = "11957-15-41";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "You entered the wrong year.\r\n" +
                "Perhaps you'll be born a thousand years from now. But let's keep the fiction out of it.\n\r\n" +
                "There cannot be more than 12 month in a year.\n\r\n\r\n";


        //        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            CheckEnteredData.isDateForLocalDate(date);

            intercept.flush();
            consoleOutput = outputStream.toString();
            System.setOut(originalOut);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Unsuccessful attempt to record from the console");
        }


//        then
        assertEquals(expected, consoleOutput);
    }

    @Test
    @DisplayName("The date entered wrong")
    public void isDateForLocalDate_enteredFalse_exception() {
//        given
        String date = "1957-2-01";


//        then
        assertThrows(IllegalArgumentException.class, () -> CheckEnteredData.isDateForLocalDate(date));
    }
}