import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {

//    private static final String dateRegex = "([0-9]{2})/([0-9]{2})/([0-9]{4)";
//    private static final Pattern pattern = Pattern.compile(dateRegex);

    private static final Scanner scanner = new Scanner(System.in);

    public MainMenu() {

    }

    public void printMainMenu() {
        System.out.println("Welcome to the Hotel Reservation Application\n");
        printDivider();
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        printDivider();
        System.out.println("Please select a number for the menu option");
    }

    public void printDivider() {
        System.out.println("---------------------------------------------");
    }

    public void enterMainMenu() {

        String userChoice = "";

        do {
            printMainMenu();
            if (scanner.hasNext())
                userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    findAndReserveARoom();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.enterAdminMenu();
                    break;
                default:
                    scanner.close();
            }

        } while (userChoice != "5");
    }

    public void findAndReserveARoom() {

        final Scanner s = new Scanner(System.in);

        Boolean isValidInput = false;
        Date checkIn = null;
        Date checkOut = null;

        checkIn = getDate("Enter checkIn Date mm/dd/yyyy. (E.g. 02/01/2020)");
        checkOut = getDate("Enter checkOut Date mm/dd/yyyy. (E.g. 02/08/2020)");
        System.out.println(checkOut);

    }

    public Date parseDateString(String inputDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

//        return pattern.matcher(inputDate).matches();

        return sdf.parse(inputDate);
    }

    public Date getDate(String message) {
        Scanner s = new Scanner(System.in);
        String userInput;

        Date date = null;

        do {
            System.out.println(message);
            userInput = s.nextLine();
            try {
                date = parseDateString(userInput);
            } catch (ParseException ex) {
                System.out.println("Invalid input. Please enter a valid date.\n");
            }
        } while (date == null);

        return date;
    }
}
