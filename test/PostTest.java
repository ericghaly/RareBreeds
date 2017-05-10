import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ericghaly on 5/3/17.
 */
public class PostTest {
    @Test
    public void addComment() {
        User testUser = new User("eric", "password", true);
        Breed newBreed = new Breed("pupper");
        Post newPost = new Post(testUser, "good boy!", newBreed);

        newPost.addComment(testUser, "look at him!");

        assertEquals(1, newPost.comments.size());

        newPost.addComment(testUser, "wow!");

        assertNotEquals(1, newPost.comments.size());
    }

    @Test
    public void getUser() {
        User testUser = new User("eric", "password", true);
        Breed testBreed = new Breed("doggo");

        Post newPost = new Post(testUser,"bork", testBreed);

        assertEquals(testUser, newPost.getUser());
        User testUser2 = new User("ry-guy", "bpdb", false);
        assertNotEquals(testUser2, newPost.getUser());

    }

    @Test
    public void getContent() {
        User testUser = new User("eric", "password", true);
        Breed testBreed = new Breed("doggo");

        Post newPost = new Post(testUser,"bork", testBreed);

        assertEquals("bork", newPost.getContent());
    }

    @Test
    public void getBreed() {
        User testUser = new User("eric", "password", true);
        Breed testBreed = new Breed("doggo");

        Post newPost = new Post(testUser,"bork", testBreed);

        assertEquals(testBreed, newPost.getBreed());
    }

    @Test
    public void toStringTest() {
        User testUser = new User("eric", "password", true);
        Breed newBreed = new Breed("pupper");
        Post newPost = new Post(testUser, "good boy!", newBreed);

        newPost.addComment(testUser, "look at him!");

        assertEquals("eric(pupper): good boy!", newPost.toString());
    }

}