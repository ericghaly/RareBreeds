import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class acts as the local front end for our Java backend for RareBreeds
 * Contains Main object to be used throughout Interface
 * Created by ericghaly on 5/1/17.
 */
public class Main {
    public LinkedList<Post> allPosts = new LinkedList<>();
    public LinkedList<Breed> allBreeds = new LinkedList<>();
    public Login newLogin = new Login();


    /**
     * This function loads dummy data for our project.
     *
     * @pre         The project has been run.
     * @post        A Breed object will be inserted into the allBreeds list.
     *
     */
    public static void loadBreed(Main main){
        Breed pupper = new Breed("pupper");
        main.allBreeds.add(pupper);
    }


    /**
     * This function loads dummy data for our project.
     *
     * @pre         The project has been run.
     * @post        A Post object and a User object will be inserted into the allPosts and Login.allUsers lists.
     *
     */
    public static void loadPost(Main main){
        User test = new User("junker", "password", true);
        main.newLogin.addUser(test);
        Post post = new Post(test, "Good ole pupper", main.allBreeds.get(0));
        main.allPosts.add(post);
        test.addPost(post);
    }


    /**
     * This function acts as the Interface of our project.
     *
     * @pre         The project has been run.
     * @post        A User will be able to access all functionality of RareBreeds.
     *
     */
    private static void Interface(){
        Main main = new Main();
        loadBreed(main);
        loadPost(main);
        System.out.println("Welcome to RareBreeds!!");
        Scanner input = new Scanner(System.in);

        while(true) {
            if ((main.newLogin == null) || (!main.newLogin.checkLogin())) {
                System.out.println("What would you like to do?");
                System.out.println("1. Login\n2. Create Account\n3. View All Posts\n4. View All Breeds");
                String choice = input.next();

                if (choice.equals("1")) {
                    main.newLogin.login();
                } else if(choice.equals("2")){
                    User newUser = main.newLogin.newUser();
                    main.newLogin.addUser(newUser);
                } else if (choice.equals("3")) {
                    System.out.println(main.allPosts.toString());
                } else if (choice.equals("4")) {
                    System.out.println(main.allBreeds.toString());
                } else {
                    System.out.println("Invalid Input!");
                }
            } else {
                System.out.println("Welcome " + main.newLogin.getCurrentUser().getName() + ", what would you like to do?");
                System.out.println("1. Logout\n2. View Your Posts\n3. Check Status\n4. New Post\n5. New Comment\n6. View Comments");
                String choice = input.next();

                if (choice.equals("1")) {
                    main.newLogin.logout();
                } else if (choice.equals("2")) {
                    main.newLogin.getCurrentUser().allPosts();
                } else if (choice.equals("3")) {
                    if (main.newLogin.getCurrentUser().getStatus()) {
                        System.out.println("You are a Breeder!");
                    } else {
                        System.out.println("You are not a Breeder!");
                    }
                }
                else if(choice.equals("4") && main.newLogin.getCurrentUser().getStatus()){
                    Post newPost = new Post(main.newLogin.getCurrentUser(), main.allBreeds);
                    main.allPosts.add(newPost);
                    main.newLogin.getCurrentUser().addPost(newPost);
                }
                else if(choice.equals("5")){
                    System.out.println("Which post would you like to comment on?");
                    System.out.println(main.allPosts.toString());
                    Post postToComment = main.allPosts.get(input.nextInt());
                    System.out.println("What would you like to comment?");
                    String commentToAdd = input.next();
                    postToComment.addComment(main.newLogin.getCurrentUser(),commentToAdd);

                }
                else if(choice.equals("6")){
                    System.out.println("For which post would you like to view the comments?");
                    System.out.println(main.allPosts.toString());
                    Post toCheck = main.allPosts.get(input.nextInt());
                    toCheck.printComments();
                } else {
                    System.out.println("Invalid Input!");
                }
            }
        }
    }

    public static void main(String[] args) {
        Interface();
    }
}
