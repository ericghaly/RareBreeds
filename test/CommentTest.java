import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ericghaly on 5/3/17.
 */
public class CommentTest {
    @Test
    public void getContent() {
        User testUser = new User("eric", "pass", true);
        Comment tester = new Comment(testUser, "such doggo!");

        assertEquals("such doggo!", tester.getContent());
        assertNotEquals("much doggo!", tester.getContent());

    }

    @Test
    public void getUser() {
        User testUser = new User("eric", "pass", true);
        Comment tester = new Comment(testUser, "such doggo!");

        assertEquals(testUser, tester.getUser());
        assertNotEquals(new User("ryan", "word", false), tester.getUser());
    }

    @Test
    public void toStringTest() {
        User testUser = new User("eric", "pass", true);
        Comment tester = new Comment(testUser, "such doggo!");

        assertEquals("eric: such doggo!", tester.toString());
        assertNotEquals("eric is such pupper!", tester.toString());
    }

}