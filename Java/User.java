import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class sets up the User object for RareBreeds.
 * Created by ericghaly on 5/1/17.
 */
public class User {
    private String name;
    private String password;
    private boolean isBreeder;
    public LinkedList<Post> posts;

    /**
     * This is the constructor for a User object.
     *
     * @param  name  Setting the name of the new User that is being added.
     * @param password Setting the password for the new User that is being added.
     * @param isBreeder Determines whether or not new User is a breeder.
     * @pre         There is a User that wishes to add a new User.
     * @post        The new User becomes added to RareBreeds.
     *
     */
    public User(String name, String password, boolean isBreeder){
        this.name = name;
        this.password = password;
        this.isBreeder = isBreeder;
        this.posts = new LinkedList<>();
    }

    /**
     * This function allows a User to change their password.
     *
     * @pre         A User object must already be created.
     * @post        The User object will call the changePassword function.
     *
     */
    private void changePassword(){
        Scanner input = new Scanner(System.in);
        System.out.println("What is your current password?");
        String oldPass = input.next();
        if(oldPass.equals(this.password)){
            System.out.println("What would you like your new password to be? ");
            String newPass = input.next();
            System.out.println("Are you sure you want to change your password to: " + newPass + "? (Y/N)");
            String YorN = input.next();
            if(YorN.equals("Y")) {
                updatePassword(newPass);
                System.out.println("Your password has been changed!");
            }
        } else {
            System.out.println("Incorrect password");
        }
    }

    /**
     * This function updates the password of a User object.
     *
     * @pre         The changePassword function must have been called.
     * @post        The function will change the password for the User.
     *
     */
    private void updatePassword(String newPassword){
        this.password = newPassword;
    }


    /**
     * This function prints the content that is stored in a User object's Post List.
     *
     * @pre         A User object must already be created.
     * @post        All Post's made by User will be displayed, if User is not a breeder a message will appear indicating so.
     *
     */
    public void allPosts(){
        if(!this.isBreeder){
            System.out.println("User is not a breeder!");
        } else {
            System.out.println(this.posts.toString());
        }
    }

    /**
     * This function recieves the content that is stored in a Comment object.
     *
     * @param post  The Post that will be added to a User's Post list.
     * @pre         A User object must already be created and a Post object must have been created.
     * @post        The Post object will be added to the User's Post list.
     *
     */
    public void addPost(Post post){
        if(post != null){
            this.posts.add(post);
        } else {
            System.out.println("Invalid Post!");
        }
    }


    /**
     * This function recieves the name that is stored in a User object.
     *
     * @return      Returns the name of a User object.
     * @pre         A User object must already be created.
     *
     */
    public String getName(){ return this.name; }


    /**
     * This function recieves the breeder status that is stored in a User object.
     *
     * @return      Returns the breeder status of a Comment object.
     * @pre         A User object must already be created.
     *
     */
    public boolean getStatus(){ return this.isBreeder; }


    /**
     * This function recieves the password that is stored in a User object.
     *
     * @return      Returns the password of a User object.
     * @pre         A User object must already be created.
     *
     */
    protected String getPassword() { return this.password; }

}
