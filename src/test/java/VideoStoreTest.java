import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoStoreTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("Fred");
    }

    @Test
    void testSingleNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3));
        assertThat("Rental records for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points").isEqualTo(customer.statement());
    }

    @Test
    void testDualNewReleaseStatement() {
        customer.addRental(new Rental(new Movie("The Cell", Movie.NEW_RELEASE), 3));
        customer.addRental(new Rental(new Movie("The Tigger Movie", Movie.NEW_RELEASE), 1));
        assertThat("Rental records for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t3.0\nYou owed 12.0\nYou earned 3 frequent renter points").isEqualTo(customer.statement());
    }

    @Test
    void testSingleChildrenStatement() {
        customer.addRental(new Rental(new Movie("The Tigger Movie", Movie.CHILDRENS), 3));
        assertThat("Rental records for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points").isEqualTo(customer.statement());
    }

    @Test
    void testMultipleRegularStatement() {
        customer.addRental(new Rental(new Movie("Plan 9 from Outer Space", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("8 1/2", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Eraserhead", Movie.REGULAR), 3));
        assertThat("Rental records for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points").isEqualTo(customer.statement());
    }

    @Test
    public void testMultipleMixStatement() {
        Customer customer = new Customer("Ali Daei");
        customer.addRental(new Rental(new Movie("Zire Nour Mah", Movie.NEW_RELEASE), 6));
        customer.addRental(new Rental(new Movie("Marmoulak", Movie.CHILDRENS), 7));
        customer.addRental(new Rental(new Movie("Offside", Movie.REGULAR), 5));

        String expected = "Rental records for Ali Daei\n"
                + "	Zire Nour Mah	18.0\n"
                + "	Marmoulak	7.5\n"
                + "	Offside	6.5\n"
                + "You owed 32.0\n"
                + "You earned 4 frequent renter points";

        assertThat(expected).isEqualTo(customer.statement());
    }

    @Test
    void testNull() {
        customer.addRental(new Rental(new Movie("The Cell", -1), 3));
        assertThat("Rental records for Fred\n\tThe Cell\t0.0\nYou owed 0.0\nYou earned 1 frequent renter points").isEqualTo(customer.statement());
    }
}
