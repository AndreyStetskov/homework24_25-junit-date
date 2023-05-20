package console;

import java.util.Scanner;

public class InputData {

    Scanner scan = new Scanner(System.in);

    public String enteredString() {
        return scan.nextLine();
    }
}
