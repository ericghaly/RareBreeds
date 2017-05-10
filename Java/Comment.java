/**
 * This class sets up the Comment object for RareBreeds
 * Created by ericghaly on 5/1/17.
 */
public class Comment {
    private User user;
    private String content;

    /**
     * This is the constructor for a Comment object.
     *
     * @param  user  Setting the User who is posting the comment.
     * @param content The content of the comment a user is posting.
     * @pre         There is a post that a user wishes to comment on.
     * @post        The new comment becomes linked to the corresponding Post.
     *
     */
    public Comment(User user, String content){
        this.user = user;
        this.content = content;
    }


    /**
     * This function recieves the content that is stored in a Comment object.
     *
     * @return      Returns the content of a Comment object.
     * @pre         A Comment object must already be created.
     *
     */
    public String getContent(){ return this.content; }


    /**
     * This function recieves the User that is stored in a Comment object.
     *
     * @return      Returns the User of a Comment object.
     * @pre         A Comment object must already be created.
     *
     */
    public User getUser(){ return this.user; }


    /**
     * This function returns the information that is stored in a Comment object.
     *
     * @return      Returns a formatted string of a Comment object.
     * @pre         A Comment object must already be created.
     * @post        User will have all necessary information stored in object.
     *
     */
    public String toString(){
        return this.getUser().getName() + ": " + this.getContent();
    }
}
