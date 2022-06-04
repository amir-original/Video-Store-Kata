public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double determineRentalLine(){
        return movie.determineAmount(daysRented);
    }

    public int determineFrequentRenterPoint() {
        return  movie.determineFrequentRenterPoints(daysRented);
    }

    public String getTitle(){
        return movie.getTitle();
    }
}
