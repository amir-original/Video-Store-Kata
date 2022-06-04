public abstract class Movie {
    private final String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    };

    abstract double determineAmount(int daysRented);

    abstract int determineFrequentRenterPoints(int daysRented);
}
