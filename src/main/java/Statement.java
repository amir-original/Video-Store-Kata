import java.util.ArrayList;
import java.util.List;

public class Statement {
    private final String customerName;
    private final List<Rental> rentals = new ArrayList<>();
    private double totalAmount;
    private int frequentRenterPoints;

    public Statement(String name) {
        this.customerName = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String generate() {
        clearTotals();
        String statement = header();
        statement += rentalLines();
        statement += footer();
        return statement;
    }

    private void clearTotals() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    private String header() {
        return String.format("Rental records for %s\n", customerName);
    }

    private String footer() {
        return String.format("You owed %.1f\nYou earned %d frequent renter points",totalAmount,frequentRenterPoints);
    }

    private String rentalLines() {
        StringBuilder rentalLines = new StringBuilder();
        for (Rental rental: rentals)
            rentalLines.append(rentalLine(rental));

        return rentalLines.toString();
    }

    private String rentalLine(Rental rental) {
        double rentalAmount = rental.determineRentalLine();
        frequentRenterPoints += rental.determineFrequentRenterPoint();
        totalAmount += rentalAmount;

        return formatRentalLine(rental, rentalAmount);
    }

    private String formatRentalLine(Rental rental, double rentalAmount) {
        return String.format("\t%s\t%.1f\n",rental.getTitle(),rentalAmount);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}
