import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoStoreTest {

    private Statement statement;
    private Movie newReleaseMovie1;
    private Movie newReleaseMovie2;
    private Movie newReleaseMovie3;
    private Movie childrenMovie1;
    private Movie regularMovie1;
    private Movie regularMovie2;
    private Movie regularMovie3;
    private Movie newReleaseMovie4;
    private Movie childrenMovie2;
    private Movie regularMovie4;

    @BeforeEach
    void setUp() {
        statement = new Statement("Fred");
        newReleaseMovie1 = new NewReleaseMovie("new release");
        newReleaseMovie2 = new NewReleaseMovie("new release");
        newReleaseMovie3 = new NewReleaseMovie("new release");
        newReleaseMovie4 = new NewReleaseMovie("new release");
        childrenMovie1 = new ChildrenMovie("children");
        childrenMovie2 = new ChildrenMovie("children");
        regularMovie1 = new RegularMovie("regular");
        regularMovie2 = new RegularMovie("regular");
        regularMovie3 = new RegularMovie("regular");
        regularMovie4 = new RegularMovie("regular");
    }

    @Test
    void testSingleNewReleaseStatement() {
        statement.addRental(new Rental(newReleaseMovie1, 3));
        statement.generate();
        assertThat(statement.getTotalAmount()).isEqualTo(9);
        assertThat(statement.getFrequentRenterPoints()).isEqualTo(2);
    }

    @Test
    void testDualNewReleaseStatement() {
        statement.addRental(new Rental(newReleaseMovie2, 3));
        statement.addRental(new Rental(newReleaseMovie3, 1));
        statement.generate();
        assertThat(statement.getTotalAmount()).isEqualTo(12);
        assertThat(statement.getFrequentRenterPoints()).isEqualTo(3);
    }

    @Test
    void testSingleChildrenStatement() {
        statement.addRental(new Rental(childrenMovie1, 3));
        statement.generate();
        assertThat(statement.getTotalAmount()).isEqualTo(1.5);
        assertThat(statement.getFrequentRenterPoints()).isEqualTo(1);
    }

    @Test
    void testMultipleRegularStatement() {
        statement.addRental(new Rental(regularMovie1, 1));
        statement.addRental(new Rental(regularMovie2, 2));
        statement.addRental(new Rental(regularMovie3, 3));
        statement.generate();
        assertThat(statement.getTotalAmount()).isEqualTo(7.5);
        assertThat(statement.getFrequentRenterPoints()).isEqualTo(3);
    }

    @Test
    public void testMultipleMixStatement() {
        Statement statement1 = new Statement("Ali Daei");
        statement1.addRental(new Rental(newReleaseMovie4, 6));
        statement1.addRental(new Rental(childrenMovie2, 7));
        statement1.addRental(new Rental(regularMovie4, 5));

        String expected = "Rental records for Ali Daei\n"
                + "	new release	18.0\n"
                + "	children	7.5\n"
                + "	regular	6.5\n"
                + "You owed 32.0\n"
                + "You earned 4 frequent renter points";

        assertThat(expected).isEqualTo(statement1.generate());
    }

}
