import java.util.Scanner;

public class Admin extends User {

    private Scanner scanner;

    public Admin() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void mainMenu(ConcertDetails concertDetails, String[] args) {
        int selection;
        do {
            System.out.println("Select an option to get started!");
            System.out.println("Press 1 to look at the concert timings");
            System.out.println("Press 2 to add a concert");
            System.out.println("Press 3 to remove a concert");
            System.out.println("Press 4 to look at the ticket costs");
            System.out.println("Press 5 to update the ticket costs for a concert");
            System.out.println("Press 6 to view the layout of the concert");
            System.out.println("Press 7 to exit");
            System.out.print("> ");

            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    displayConcertTimings(concertDetails);
                    break;
                case 2:
                    addConcert(concertDetails);
                    break;
                case 3:
                    removeConcert(concertDetails);
                    break;
                case 4:
                    showTicketPrices(concertDetails);
                    break;
                case 5:
                    // updateTicketCosts(concertDetails);
                    break;
                case 6:
                    showSeatsLayout(concertDetails);
                    break;
                case 7:
                    System.out.println("Exiting Admin Mode");
                    // scanner.close();  // Properly close scanner to avoid resource leaks
                    return; // Exit the admin menu
                default:
                    System.out.println("Invalid input. Please select a valid option.");
                    break;
            }
        } while (selection != 7);
    }

    private void addConcert(ConcertDetails concertDetails) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        System.out.println("To add a new concert please enter the details:");

        System.out.print("Date: ");
        String date = scanner.nextLine(); 

        System.out.print("Timing: ");
        String timing = scanner.nextLine();

        System.out.print("Artist Name: ");
        String artist = scanner.nextLine();

        System.out.print("Venue: ");
        String venueName = scanner.nextLine();

        System.out.print("Left zone price: ");
        double leftPrice = scanner.nextDouble();

        System.out.print("Middle zone price: ");
        double middlePrice = scanner.nextDouble();

        System.out.print("Right zone price: ");
        double rightPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline left-over

        Venue venue = TicketManagementEngine.venue; // Reuse the venue instance from the engine
        int totalSeats = venue.getRows() * (venue.getLeft() + venue.getMiddle() + venue.getRight());
        Concert newConcert = new Concert(date, artist, timing, venueName, totalSeats, 0, leftPrice, middlePrice, rightPrice, venue);
        concertDetails.addConcert(newConcert);
        System.out.println("New concert added successfully.");
    }

    private void removeConcert(ConcertDetails concertDetails) {
        System.out.println("Select a concert number to remove.");
        displayConcertTimings(concertDetails);  // Show available concerts with their current booking status
        int concertIndex = scanner.nextInt() - 1;  // Convert 1-based user input to 0-based index

        if (concertIndex >= 0 && concertIndex < concertDetails.getCount()) {
            Concert concert = concertDetails.getConcerts()[concertIndex];
            if (concert.getSeatsBooked() > 0) {
                System.out.println("Concert cannot be removed as tickets are booked for this concert.");
            } else {
                // Remove concert logic
                concertDetails.removeConcert(concertIndex);
                System.out.println("Concert removed successfully.");
            }
        } else {
            System.out.println("Invalid concert selection.");
        }
    }

    /**
    private void updateTicketCosts(ConcertDetails concertDetails) {
        int concertIndex = selectConcert(concertDetails);
        System.out.println("Enter new prices for Left, Middle, Right zones:");
        int left = scanner.nextInt();
        int middle = scanner.nextInt();
        int right = scanner.nextInt();

        Concert concert = concertDetails.getConcerts()[concertIndex];
        concert.setLeftZonePrice(left);
        concert.setMiddleZonePrice(middle);
        concert.setRightZonePrice(right);
        System.out.println("Ticket prices updated successfully.");
    }

    private int selectConcert(ConcertDetails concertDetails) {
        displayConcertTimings(concertDetails);
        System.out.println("Select a concert number:");
        return scanner.nextInt() - 1;  // Assuming user input is 1-based index
    } */
}
