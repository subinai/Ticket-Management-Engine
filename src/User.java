import java.util.Scanner;

public abstract class User {

    private Scanner scanner = new Scanner(System.in);

    // private static Venue venue;

    // Abstract method that each user type must implement to provide their specific menu system
    public abstract void mainMenu(ConcertDetails concertDetails, String[] args);

    // Common method to display concert timings, potentially useful for both Customer and Admin
    public void displayConcertTimings(ConcertDetails concertDetails) {
        System.out.println("Show Timings");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-15s%-30s%-15s%-15s%-15s\n", "#", "Date", "Artist Name", "Timing", "Venue Name", "Total Seats", "Seats Booked", "Seats Left");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        
        Concert[] concerts = concertDetails.getConcerts();
        for (int i = 0; i < concertDetails.getCount(); i++) {
            Concert concert = concerts[i];
            System.out.printf("%-5d%-15s%-15s%-15s%-30s%-15d%-15d%-15d\n",
                              i + 1,
                              concert.getDate(),
                              concert.getArtistName(),
                              concert.getTiming(),
                              concert.getVenueName(),
                              concert.getTotalSeats(),
                              concert.getSeatsBooked(),
                              concert.getTotalSeats() - concert.getSeatsBooked());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

    }

    public void showTicketPrices(ConcertDetails concertDetails) {
        System.out.println("Enter the concert number to see the prices for the seats");
        displayConcertTimings(concertDetails);
        System.out.print("> ");
        int concertIndex = scanner.nextInt() - 1;  // Convert to 0-based index

        if (concertIndex >= 0 && concertIndex < concertDetails.getCount()) {
            Concert concert = concertDetails.getConcerts()[concertIndex];
            System.out.println("Seat Costs");
            System.out.println("-----------------------");
            System.out.println("Left Zone:   AUD " + concert.getLeftZonePrice());
            System.out.println("Middle Zone: AUD " + concert.getMiddleZonePrice());
            System.out.println("Right Zone:  AUD " + concert.getRightZonePrice());
            System.out.println("-----------------------");
        } else {
            System.out.println("Invalid concert selection.");
        }
    }

    public void showSeatsLayout(ConcertDetails concertDetails) {
        System.out.println("Select a concert that's price needs to be updated");
        displayConcertTimings(concertDetails);
        System.out.print("> ");
        int concertIndex = scanner.nextInt() - 1;  // Convert to 0-based index

        if (concertIndex >= 0 && concertIndex < concertDetails.getCount()) {
            Concert concert = concertDetails.getConcerts()[concertIndex];
            concert.getVenue().showSeatingLayout();
        } else {
            System.out.println("Invalid concert selection.");
        }
    }

}
