package entity.organization.educational;

import console.InputData;
import console.OutputMassage;
import date.control.CheckEnteredDate;
import date.AgeCalculationUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.Stream;

public class Service {

    private final OutputMassage massage;
    private final InputData input;
    private final CheckEnteredDate checkDate;
    private Type type;


    public Service() {
        massage = new OutputMassage();
        input = new InputData();
        checkDate = new CheckEnteredDate();
    }


    public void searchByBirthdate() {

        massage.beforeEnteredBirthday();

        String birthday = input.enteredString();

        ageFromByBirthdate(birthday);
    }

    private void ageFromByBirthdate(String birthday) {

        if (checkDate.isDateForLocalDate(birthday)) massage.accessGranted();
        else {
            massage.accessDenied();

            this.searchByBirthdate();

            return;
        }

        checkDate.checkForIncompleteInput(birthday);

        LocalDate birthdate = LocalDate.parse(birthday);
        LocalDate today = LocalDate.now();

        int age = AgeCalculationUtil.ageCalculation(today, birthdate);

        if (age < 0 || age > 115) {
            this.searchByBirthdate();

            return;
        }

        if (age > 85) massage.bigAge(age);
        else massage.littleAge(age);

        Type[] schools = Type.values();

        for (Type school : schools) {
            if (school.getMinAge() < age && school.getMaxAge() > age) {
                type = school;
            }
        }

        type.recommended(age);

        massage.beforeListOfInstructions();

        resultOfSearchByBirthdate(age);
    }

    private void resultOfSearchByBirthdate(int age) {

        try(BufferedReader bufferedReader = Files.newBufferedReader(Path.of("resources/organization/educational.txt"))) {

            Stream<String> firm = bufferedReader.lines();

            firm.map(o -> o.replaceAll("\\s+", " "))
                    .skip(2)
                    .forEach(organization -> {

                String[] data = organization.split(" \\| ");

                EducationalDTO suitable = new EducationalDTO(data);

                if (!suitable.isCurrent()) {
                    return;
                }

                if (suitable.getAgeLimit().equals("unlimited")) {
                    massage.getInstruction(suitable);
                }
                else if (suitable.getAgeLimit().startsWith("more")) {
                    int limit = Integer.parseInt(suitable.getAgeLimit().replace("more ", ""));

                    if (age >= limit) {
                        massage.getInstruction(suitable);
                    }
                }
                else {
                    String[] ages = suitable.getAgeLimit().split("-");

                    int limitFrom = Integer.parseInt(ages[0]);
                    int limitTo = Integer.parseInt(ages[1]);

                    if (age >= limitFrom && age <= limitTo) {
                        massage.getInstruction(suitable);
                    }
                }
            });
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Something strange is going on with this file", e);
        }
    }
}
