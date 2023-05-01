package organization.educational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    Service service;

    @ParameterizedTest
    @DisplayName("The date entered exactly")
    @ValueSource(strings = {"2020-01-01",
            "2020-1-01",
            "2020-01-1",
            "2020-1-1"
    })
    public void searchByBirthdate_correctDate_Success(String birthday ) {
//        given
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "Access granted\n\r\n" +
                "Your age is 3 years young\n" +
                "Creche, Nurse school or Preschool recommended for you\n\n" +
                "The following educational institutions would be suitable for you:\r\n" +
                "id1 Pupkin and Ko (EIN: 96-3950365) is private educational institution with a staff of 3500000 has been worked since 1888, can accept any applicant of any age!! This school located in USA at 3819 Westgate Ave, West Palm Beach, Florida FL33409. You can contact them by phone +1-(978)-448-0650\r\n" +
                "id4 Key Point Academy Coral-Gables (EIN: 50-9100531) is state organization with a staff of 550 has been worked since 2003, can accept any applicant between the ages of 1 and 5. This school located in USA at 3066 SW 38th Ave Coral Gables, Florida FL33146. You can contact them by phone +1-(305)-549-8125\r\n" +
                "id8 Pacific Northern Academy (EIN: 14-3182558) is private educational institution with a staff of 65 has been worked since 2021, can accept any applicant between the ages of 2 and 18. This school located in USA at 2511 Sentry Drive, Suite 100 Anchorage, Alaska Al99507. You can contact them by phone +1-(907)-333-1080\r\n" +
                "id14 Cambridge-Ellis School (EIN: 19-6081081) is state organization with a staff of 110 has been worked since 1999, can accept any applicant between the ages of 1 and 3. This school located in USA at 80 Trowbridge St. Cambridge, Massachusetts MA02138. You can contact them by phone +1-(617)-354-0014\r\n" +
                "id16 The School for the Talented and Gifted (EIN: 11-3005615) is private educational institution with a staff of 460 has been worked since 1986, can accept any applicant between the ages of 2 and 16. This school located in USA at 1201 E. Eighth-Street, Dallas, Texas TX75203. You can contact them by phone +1-(972)-925-5970\r\n" +
                "id20 The Actor's School (EIN: 46-4545464) is private educational institution with a staff of 40 has been worked since 1946, can accept any applicant of any age!! This school located in USA at 128 Holiday-Ct #128, Franklin, Tennessee TN37067. You can contact them by phone +1-(615)-500-7661\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            try {
                Method method = Service.class.getDeclaredMethod("ageFromByBirthdate", String.class);
                method.setAccessible(true);
                method.invoke(service, birthday);
            } catch (Exception e) {
                e.printStackTrace();
            }

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
    public void searchByBirthdate_Border_Success() {
//        given
        String birthday = "2023-04-18";
        String consoleOutput = null;
        PrintStream originalOut = System.out;
        String expected = "Access granted\n\r\n" +
                "Your age is 0 years young\n" +
                "Creche, Nurse school or Preschool recommended for you\n\n" +
                "The following educational institutions would be suitable for you:\r\n" +
                "id1 Pupkin and Ko (EIN: 96-3950365) is private educational institution with a staff of 350000 has been worked since 1888, can accept any applicant of any age!! This school located in USA at 3819 Westgate Ave, West Palm Beach, Florida FL33409. You can contact them by phone +1-(978)-448-0650\r\n" +
                "id20 The Actor's School (EIN: 46-4545464) is private educational institution with a staff of 40 has been worked since 1946, can accept any applicant of any age!! This school located in USA at 128 Holiday-Ct #128, Franklin, Tennessee TN37067. You can contact them by phone +1-(615)-500-7661\r\n";


//        when
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream intercept = new PrintStream(outputStream);
            System.setOut(intercept);

            try {
                Method method = Service.class.getDeclaredMethod("ageFromByBirthdate", String.class);
                method.setAccessible(true);
                method.invoke(service, birthday);
            } catch (Exception e) {
                e.printStackTrace();
            }

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
}