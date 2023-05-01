package organization.educational;

import organization.AgeCalculation;
import organization.CheckEnteredData;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Stream;

public class Service {
    public void searchByBirthdate() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please, enter your birthday in the format YYYY-MM-DD, for example 1990-01-03");

        String birthday = scan.nextLine();

        ageFromByBirthdate(birthday);
    }

    private static void ageFromByBirthdate(String birthday) {
        if (CheckEnteredData.isDateForLocalDate(birthday)) System.out.println("Access granted\n");
        else {
            System.out.println("Access  denied");
            System.out.println("Try again and see example carefully please\n");

            new Service().searchByBirthdate();

            return;
        }
        String [] divideDate = birthday.split("-");

        if (divideDate[1].length() == 1) birthday = birthday.substring(0, 5) + "0" + birthday.substring(5);
        if (divideDate[2].length() == 1) birthday = birthday.substring(0, 8) + "0" + birthday.substring(8);

        LocalDate birthdate = LocalDate.parse(birthday);
        LocalDate today = LocalDate.now();

        int age = AgeCalculation.ageCalculation(today, birthdate);

        if (age < 0) {
            new Service().searchByBirthdate();

            return;
        }

        if (age > 85) System.out.printf("Are you definitely %d years old?! You're in great shape!)\n", age);
        else System.out.printf("Your age is %s years young\n", age);

        if (age < 6) System.out.printf("%s recommended for you\n\n", Type.PRE_SCHOOL.getSchool());
        else if (age < 12) System.out.printf("%s recommended for you\n\n", Type.ELEMENTARY_SCHOOL.getSchool());
        else if (age < 15) System.out.printf("%s recommended for you\n\n", Type.MIDDLE_SCHOOL.getSchool());
        else if (age < 18) System.out.printf("%s recommended for you\n\n", Type.HIGH_SCHOOL.getSchool());
        else System.out.printf("%s recommended for you\n\n", Type.UNIVERSITY.getSchool());

        System.out.println("The following educational institutions would be suitable for you:");

            Service.resultOfSearchByBirthdate(age);
    }

    private static void resultOfSearchByBirthdate(int age) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Path.of("resources/organization/educational.txt"));
            Stream<String> firm = bufferedReader.lines();

            firm.map(o -> o.replaceAll("\\s+", " "))
                    .skip(2)
                    .forEach(organization -> {

                String[] data = organization.split(" \\| ");

                if (data[11].equals("true")) {
                    if (data[3].equals("unlimited")) {
                        Educational suitable = new Educational(data[1], data[2].replace('_', ' '), data[3], data[4].replace('_', ' '), Boolean.parseBoolean(data[5]), Integer.parseInt(data[6]), data[7], data[8].replace('_', ' '), data[9].replace('_', ' '), data[10], Boolean.parseBoolean(data[11]));
                        System.out.printf("id" + data[0] + " ");
                        System.out.println(suitable);
                    }
                    else if (data[3].startsWith("more")) {
                        int limit = Integer.parseInt(data[3].replace("more_", ""));

                        if (age >= limit) {
                            Educational suitable = new Educational(data[1], data[2].replace('_', ' '), data[3].replace('_', ' '), data[4].replace('_', ' '), Boolean.parseBoolean(data[5]), Integer.parseInt(data[6]), data[7], data[8].replace('_', ' '), data[9].replace('_', ' '), data[10], Boolean.parseBoolean(data[11]));
                            System.out.printf("id" + data[0] + " ");
                            System.out.println(suitable);
                        }
                    }
                    else {
                        String[] ages = data[3].split("-");
                        int limitFrom = Integer.parseInt(ages[0]);
                        int limitTo = Integer.parseInt(ages[1]);

                        if (age >= limitFrom && age <= limitTo) {
                            Educational suitable = new Educational(data[1], data[2].replace('_', ' '), data[3], data[4].replace('_', ' '), Boolean.parseBoolean(data[5]), Integer.parseInt(data[6]), data[7], data[8].replace('_', ' '), data[9].replace('_', ' '), data[10], Boolean.parseBoolean(data[11]));
                            System.out.printf("id" + data[0] + " ");
                            System.out.println(suitable);
                        }
                    }
                }
            });
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Something strange is going on with this file", e);
        }
    }
}
