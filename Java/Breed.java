/**
 * This class sets up the Breed object for RareBreeds
 * Created by ericghaly on 5/1/17.
 */
public class Breed {
    private String name;

    /**
     * This is the constructor for a Breed object.
     *
     * @param  name  Setting the name of the new Breed that is being added.
     * @pre         There is a User that wishes to add a new Breed.
     * @post        The new Breed becomes added to RareBreeds.
     *
     */
    public Breed(String name){
       this.name = name;
    }


    /**
     * This function recieves the name of a Breed object.
     *
     * @return      Returns the name of a Breed object.
     * @pre         A Breed object must already be created.
     *
     */
    public String getName(){ return this.name; }


    /**
     * This function returns the information that is stored in a Breed object.
     *
     * @return      Returns a formatted string of a Breed object.
     * @pre         A Breed object must already be created.
     * @post        User will have all necessary information stored in object.
     *
     */
    public String toString(){
        return getName();
    }
}
