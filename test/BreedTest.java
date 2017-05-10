import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ericghaly on 5/3/17.
 */
public class BreedTest {
    @Test
    public void getName() {
        Breed tester = new Breed("Doggo");
        Breed tester2 = new Breed("Pupper");
        assertEquals("Doggo", tester.getName());
        assertNotEquals("Pupperino", tester2.getName());
    }

    @Test
    public void toStringTest() {
        Breed tester = new Breed("Doggo");
        Breed tester2 = new Breed("Pupper");
        assertEquals("Doggo",tester.toString());
        assertNotEquals("Pupperino", tester2.toString());
    }

}