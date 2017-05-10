import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ericghaly on 5/3/17.
 */
public class UserTest {
    @Test
    public void addPost() {
        User newUser = new User("eric", "password", true);
        Breed newBreed = new Breed("doggo");
        Post newPost = new Post(newUser, "incredible!", newBreed);

        assertEquals(0, newUser.posts.size());

        newUser.addPost(newPost);

        assertEquals(1, newUser.posts.size());
    }

    @Test
    public void getName() {
        User testUser = new User("Joe", "rulez", false);
        assertEquals("Joe", testUser.getName());
    }

    @Test
    public void getStatus() {
        User testUser1 = new User("Joe", "rulez", false);
        assertEquals(false, testUser1.getStatus());

        User testUser2 = new User("Will", "dabs", true);
        assertEquals(true, testUser2.getStatus());
    }

    @Test
    public void getPassword() {
        User testUser1 = new User("Joe", "rulez", false);
        assertEquals("rulez", testUser1.getPassword());

        User testUser2 = new User("Will", "dabs", true);
        assertEquals("dabs", testUser2.getPassword());
    }

}