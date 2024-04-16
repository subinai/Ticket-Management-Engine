import java.util.Scanner;

public class Customer extends User {
    
    private Scanner scanner;
    // private static Venue venue;

    public Customer() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void mainMenu(ConcertDetails concertDetails, String[] args) {
        int selection;
        do {
            System.out.println("Select an option to get started!");
            System.out.println("Press 1 to look at the show timings");
            System.out.println("Press 2 to look at the ticket costs");
            System.out.println("Press 3 to view the layout of the concert");
            System.out.println("Press 4 to book seats");
            System.out.println("Press 5 to see the price for selected seats");
            System.out.println("Press 6 to exit");
            System.out.print("> ");

            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    displayConcertTimings(concertDetails);
                    break;
                case 2:
                    showTicketPrices(concertDetails);
                    break;
                case 3:
                    showSeatsLayout(concertDetails);
                    break;
                case 4:
                    bookSeats(concertDetails);
                    break;
                case 5:
                    // calculateTotalPrices(concertDetails);
                    break;
                case 6:
                    System.out.println("Exiting Customer Mode");
                    // scanner.close();  // Properly close scanner to avoid resource leaks
                    return; // Exit the admin menu
                default:
                    System.out.println("Invalid input. Please select a valid option.");
                    break;
            }
        } while (selection != 6);
    }

    private void bookSeats(ConcertDetails concertDetails) {
        System.out.println("Select a concert that's price needs to be updated");
        displayConcertTimings(concertDetails);
        System.out.print("> ");
        
        int concertIndex = scanner.nextInt() - 1;

        scanner.nextLine();

        if (concertIndex >= 0 && concertIndex < concertDetails.getCount()) {
            Concert concert = concertDetails.getConcerts()[concertIndex];
            Venue venue = concert.getVenue(); // Assuming concert holds a reference to its venue
            venue.seatSelectionInitiated(true);

            System.out.println("Continue to the seat selection!"); 
            System.out.println("You can select the seat that are empty and marked by _");

            String input;
            do {
                venue.showSeatingLayout();
                System.out.println("Press W/S/A/D to move up/down/left/right. Press Z to select, Q to quit.");
                System.out.print("> ");
                input = scanner.nextLine().toUpperCase();

                switch (input) {
                    case "W":
                    case "A":
                    case "S":
                    case "D":
                        if (!venue.moveSeat(input.charAt(0))) {
                            System.out.println("Invalid move.");
                        }
                        break;
                    case "Z":
                        venue.selectSeat();  // Assuming selectSeat marks the seat and moves the selector
                        break;
                    case "Q":
                        venue.seatSelectionInitiated(false);    // Reset seat selection status after booking
                        System.out.println("Exiting seat selection.");
                        break;
                    default:
                        System.out.println("Invalid input. Press W/S/A/D to move up/down/left/right. Press Z to select, Q to quit.");
                        break;
                }
            } while (!input.equals("Q"));
        } else {
            System.out.println("Invalid concert selection.");
        }
    }

    /**
    private void calculateTotalPrices(ConcertDetails concertDetails) {
        displayConcertTimings(concertDetails);
        System.out.println("Enter the concert number to calculate total prices for the booked seats:");
        int concertIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (concertIndex >= 0 && concertIndex < concertDetails.getCount()) {
            Concert concert = concertDetails.getConcerts()[concertIndex];
            venue.seatSelectionInitiated(true);
            System.out.println("Continue to the seat selection!");
            System.out.println("You can select the seat that is empty and marked by _");
            System.out.println("Press W/S/A/D to move up/down/left/right. Press Z to select, Q to quit.");

            // Implement pricing calculation based on the number of seats booked in each zone
        } else {
            System.out.println("Invalid concert selection or no bookings made for this concert.");
        }
    }
    */
}