import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ericghaly on 5/3/17.
 */
public class LoginTest {
    @Test
    public void checkLogin() {
        Login newLogin = new Login();
        newLogin.logout();
        assertEquals(false, newLogin.checkLogin());
    }

    @Test
    public void getCurrentUser() {
        Login login = new Login();
        assertEquals(null, login.getCurrentUser());
    }

    @Test
    public void addUser() {
        User testUser = new User("eric", "word", false);
        Login login = new Login();
        login.addUser(testUser);
        assertEquals(1, login.userList.size());
    }

}