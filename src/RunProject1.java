/**
 * Runs the CollectionManager.Java file, and is the entry point of the project.
 *
 * @author Woogyeom Sim, Aravind Chundu
 */

public class RunProject1 {
    /**
     * Initializes the CollectManager and starts the program
     */
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        System.out.println("Collection Manager is up running.\n");
        collectionManager.run();
    }
}
