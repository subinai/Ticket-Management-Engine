public class ConcertDetails {
    private Concert[] concerts;
    private int count;  // This will track the number of concerts currently stored

    public ConcertDetails(int initialCapacity) {
        this.concerts = new Concert[initialCapacity];
        this.count = 0;
    }

    public void addConcert(Concert concert) {
        // Check if the array needs to be expanded
        if (count >= concerts.length) {
            resize();
        }
        concerts[count++] = concert;
    }

    private void resize() {
        Concert[] newConcerts = new Concert[concerts.length * 2];
        for (int i = 0; i < concerts.length; i++) {
            newConcerts[i] = concerts[i];
        }
        concerts = newConcerts;
    }

    public Concert[] getConcerts() {
        return concerts;
    }

    public int getCount() {
        return count;
    }

    public void removeConcert(int index) {
        if (index >= 0 && index < count) {
            for (int i = index; i < count - 1; i++) {
                concerts[i] = concerts[i + 1];
            }
            concerts[count - 1] = null;
            count--;
        }
    }
}
