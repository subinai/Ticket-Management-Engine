import java.util.Scanner;

public class TicketManagementEngine {

    private static final Scanner keyboard = new Scanner(System.in);
    public static Venue venue;

    public static void main(String[] args){
        TicketManagementEngine tme = new TicketManagementEngine();


        if(tme.checkLayoutArgs(args)){
            System.out.println("Invalid Inputs to set layout. Exiting the program now.");
        }else {
            int rows = Integer.parseInt(args[0]);
            int left = Integer.parseInt(args[1]);
            int middle = Integer.parseInt(args[2]);
            int right = Integer.parseInt(args[3]);

            venue = new Venue(rows, left, middle, right);

            tme.displayMessage();

            ConcertDetails concertDetails = tme.preLoadConcertDetails(rows, left, middle, right);

            // Start the user mode menu (customer/admin)
            tme.showModeMenu(concertDetails, args);
        }
    }

    public void showModeMenu(ConcertDetails concertDetails, String[] args) {
        int input;
        do {
            System.out.println("Select an option to get started!");
            System.out.println("Press 1 to enter Customer mode");
            System.out.println("Press 2 to enter Admin mode");
            System.out.println("Press 3 to exit");
            System.out.print("> ");

            input = keyboard.nextInt();

            switch (input) {
                case 1:
                    Customer customer = new Customer();
                    customer.mainMenu(concertDetails, args);
                    break;

                case 2:
                    Admin admin = new Admin();
                    admin.mainMenu(concertDetails, args);
                    break;

                case 3:
                    System.out.println("Goodbye from the Ticket Management System! See you next time!");
                    keyboard.close();  // Closing scanner on exit
                    break;
                
                default:
                    System.out.println("Invalid Input. Please try again."); 
            }
        } while (input != 3);
    }

    private ConcertDetails preLoadConcertDetails(int rows, int left, int middle, int right) {
        int initialCapacity = 10; // Example capacity
        ConcertDetails concertDetails = new ConcertDetails(initialCapacity);
        int totalSeats = venue.getRows() * (venue.getLeft() + venue.getMiddle() + venue.getRight());
        concertDetails.addConcert(new Concert(
            "2023-07-01", "Taylor Swift", "1700-1900", "Melbourne Cricket Ground", totalSeats, 0, 200, 500, 250, new Venue(rows, left, middle, right)));
        concertDetails.addConcert(new Concert(
            "2023-07-02", "Taylor Swift", "1900-2100", "Marvel Stadium", totalSeats, 0, 100, 300, 150, new Venue(rows, left, middle, right)));
        return concertDetails;
    }

    private boolean checkLayoutArgs(String[] args) {
        if (args.length < 4) {
            System.out.println("Error: Not enough arguments provided. You must provide values for rows, left, middle, and right.");
            return true;
        }
        for (int i = 0; i < 4; i++) {
            try {
                int argValue = Integer.parseInt(args[i]);
                if (argValue <= 0) {
                    System.out.println("Error: Layout arguments must be greater than zero.");
                    return true;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Layout arguments must be numeric.");
                return true;
            }
        }
        return false;
    }

    public void displayMessage(){
        System.out.println("Welcome to a revised version of Taylor Swift's Eras tour.");

        System.out.print("\n" +
                " _____                _____                  ________  ___ _____ \n" +
                "|  ___|              |_   _|                |_   _|  \\/  |/  ___|\n" +
                "| |__ _ __ __ _ ___    | | ___  _   _ _ __    | | | .  . |\\ `--. \n" +
                "|  __| '__/ _` / __|   | |/ _ \\| | | | '__|   | | | |\\/| | `--. \\\n" +
                "| |__| | | (_| \\__ \\   | | (_) | |_| | |      | | | |  | |/\\__/ /\n" +
                "\\____/_|  \\__,_|___/   \\_/\\___/ \\__,_|_|      \\_/ \\_|  |_/\\____/ \n" +
                "                                                                 \n" +
                "                                                                 \n");
    }
}
