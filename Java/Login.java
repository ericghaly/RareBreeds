import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class sets up the Login object for RareBreeds
 * Created by ericghaly on 5/1/17.
 */
public class Login {
    protected LinkedList<User> userList;
    private boolean isLoggedIn;
    private User currentUser;


    /**
     * This is the constructor for a Login object.
     *
     * @pre         There is a User that wishes to Login.
     * @post        The User will be logged into RareBreeds.
     *
     */
    public Login(){
        this.userList = new LinkedList<>();
        this.isLoggedIn = false;
    }


    /**
     * This is the login function for a Login object.
     *
     * @pre         There is a User that wishes to Login.
     * @post        The User will be logged into RareBreeds or will be given an error message.
     *
     */
    public void login(){
        User testUser = new User("eric", "password", true);
        userList.add(testUser);

        User testUser2 = new User("ryan", "password", false);
        userList.add(testUser2);

        Scanner input = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = input.next();
        System.out.println("Enter your password: ");
        String password = input.next();
        for(User toCheck : this.userList){
            if(toCheck.getName().equals(username)){
                if(toCheck.getPassword().equals(password)){
                    this.isLoggedIn = true;
                    this.currentUser = toCheck;
                    return;
                }
            }
        }
        System.out.println("Invalid Username or Password!");
    }


    /**
     * This is the logout function for a Login object.
     *
     * @pre         There is a User, who is already logged in, that wishes to logout.
     * @post        The User will be logged out of RareBreeds.
     *
     */
    public void logout(){
        this.isLoggedIn = false;
        this.currentUser = null;
    }


    /**
     * This function checks to see whether or not a User is logged into RareBreeds.
     *
     * @return      True if User is logged in, False if User is logged out.
     *
     */
    public boolean checkLogin(){ return this.isLoggedIn; }


    /**
     * This function checks to see which User is logged into RareBreeds.
     *
     * @return      User that is logged into RareBreeds, or null if no User is logged in.
     *
     */
    public User getCurrentUser(){
        return this.currentUser;
    }


    /**
     * This function allows a user to create a new User for RareBreeds.
     *
     * @return      Returns the new User object that will be created.
     * @pre         A user wants to create a new User object.
     * @post        A new User object is created and added to RareBreeds.
     *
     */
    public User newUser(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your new username: ");
        String newName = input.next();
        System.out.println("Enter your new password: ");
        String newPassword = input.next();
        System.out.println("Are you a breeder? (Y/N)");
        String YorN = input.next();
        boolean breeder;
        if(YorN.equals("Y")){
            breeder = true;
        } else {
            breeder = false;
        }
        return (new User(newName, newPassword, breeder));
    }


    /**
     * This function adds a User to the User List of RareBreeds.
     *
     * @pre     A new User object must have been created.
     * @post    The new User will be added to the User List.
     *
     */
    public void addUser(User userToAdd){
        this.userList.add(userToAdd);
    }
}
