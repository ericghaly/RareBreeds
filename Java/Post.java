import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class sets up the Post object for RareBreeds
 * Created by ericghaly on 5/1/17.
 */
public class Post {
    private User user;
    private String content;
    public LinkedList<Comment> comments;
    private Breed breed;

    /**
     * This is the testing constructor for a Post object.
     *
     * @param  user  Setting the User of the new Post that is being added.
     * @param content Setting the content for the new Post that is being added.
     * @param breed Setting the Breed for the new Post that is being added.
     * @pre         There is a User that wishes to create a new Post.
     * @post        The new Post becomes added to RareBreeds.
     *
     */
    public Post(User user, String content, Breed breed){
        this.user = user;
        this.content = content;
        this.comments = new LinkedList<>();
        this.breed = breed;
    }


    /**
     * This is the User constructor for a Post object.
     *
     * @param  user  Setting the User of the new Post that is being added.
     * @param breedList A list of all current Breed's currently in RareBreeds.
     * @pre         There is a User that wishes to create a new Post.
     * @post        The new Post becomes added to RareBreeds.
     *
     */
    public Post(User user, LinkedList<Breed> breedList){
        Scanner input = new Scanner(System.in);
        System.out.println("Which breed are you looking to sell?");
        System.out.println(breedList.toString());
        this.breed = breedList.get(input.nextInt());
        System.out.println("What do you want to say about the dog you're selling? ");
        this.content = input.next();
        this.user = user;
        this.comments = new LinkedList<>();
    }


    /**
     * This function allows a User to add a Comment to a Post.
     *
     * @pre         A Post object must already be created.
     * @post        The Post object will create a new Comment object and link it to the Post.
     *
     */
    public void addComment(User user, String content){
        Comment newComment = new Comment(user, content);
        comments.add(newComment);
    }


    /**
     * This function recieves the User that is stored in a Post object.
     *
     * @return      Returns the User of a Post object.
     * @pre         A Post object must already be created.
     *
     */
    public User getUser(){ return this.user; }


    /**
     * This function recieves the content that is stored in a Post object.
     *
     * @return      Returns the content of a Post object.
     * @pre         A Post object must already be created.
     *
     */
    public String getContent(){ return this.content; }


    /**
     * This function recieves the Breed that is stored in a Post object.
     *
     * @return      Returns the Breed of a Post object.
     * @pre         A Post object must already be created.
     *
     */
    public Breed getBreed(){ return this.breed; }


    /**
     * This function prints the Comments that are stored in a User object's Post List.
     *
     * @pre         A Post object must already be created.
     * @post        All Comments linked to the Post will be displayed.
     *
     */
    public void printComments(){
        System.out.println(comments.toString());
    }


    /**
     * This function returns the information that is stored in a Post object.
     *
     * @return      Returns a formatted string of a Post object.
     * @pre         A Post object must already be created.
     * @post        User will have all necessary information stored in object.
     *
     */
    public String toString(){
        return (this.getUser().getName() + "(" + this.getBreed().toString()+"): " + this.getContent());
    }
}
