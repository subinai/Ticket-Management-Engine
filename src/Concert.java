public class Concert {
    private String date;
    private String artistName;
    private String timing;
    private String venueName;
    private int totalSeats;
    private int seatsBooked;
    private double leftZonePrice;
    private double middleZonePrice;
    private double rightZonePrice;   
    private Venue venue;

    public Concert(String date, String artistName, String timing, String venueName,
                   int totalSeats, int seatsBooked, double leftZonePrice,
                   double middleZonePrice, double rightZonePrice, Venue venue) {
        this.date = date;
        this.artistName = artistName;
        this.timing = timing;
        this.venueName = venueName;
        this.totalSeats = totalSeats;
        this.seatsBooked = seatsBooked;
        this.leftZonePrice = leftZonePrice;
        this.middleZonePrice = middleZonePrice;
        this.rightZonePrice = rightZonePrice;
        this.venue = venue;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTiming() {
        return timing;
    }

    public String getVenueName() {
        return venueName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public int getSeatsLeft() {
        return totalSeats - seatsBooked;
    }

    public double getLeftZonePrice() {
        return leftZonePrice;
    }

    public double getMiddleZonePrice() {
        return middleZonePrice;
    }

    public double getRightZonePrice() {
        return rightZonePrice;
    }

    public Venue getVenue() {
        return venue;
    }

    // Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public void setLeftZonePrice(double leftZonePrice) {
        this.leftZonePrice = leftZonePrice;
    }

    public void setMiddleZonePrice(double middleZonePrice) {
        this.middleZonePrice = middleZonePrice;
    }

    public void setRightZonePrice(double rightZonePrice) {
        this.rightZonePrice = rightZonePrice;
    }
}
