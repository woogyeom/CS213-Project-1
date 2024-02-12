import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Manages a collection of music albums, including adding, removing, and rating albums.
 * Reads commands from input and executes operations such as printing the collection
 * sorted by date, genre, or rating.
 *
 * @author Woogyeom Sim, Aravind Chundu
 */
public class CollectionManager {
    private Collection collection;

    /**
     * Constructs a CollectionManager object and initializes the album collection.
     */
    public  CollectionManager() {
        this.collection = new Collection();
    }

    /**
     * Processes commands from a file to manage the album collection. Supports adding,
     * removing, and rating albums, as well as printing the collection in various orders.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] tokens = input.split(",");

            switch (tokens[0]) {
                case "A": // add an album
                    addAlbum(tokens);
                    break;
                case "D": // remove the album
                    removeAlbum(tokens);
                    break;
                case "R": // rate the album
                    rateAlbum(tokens);
                    break;
                case "PD": // print by date
                    collection.printByDate();
                    break;
                case "PG": // print by genre
                    collection.printByGenre();
                    break;
                case "PR": // print by rating
                    collection.printByRating();
                    break;
                case "Q":  // quit
                    System.out.println("Collection Manager terminated.");
                    return;
                case "PA":
                    collection.pirntAll();
                    break;
                case "": // empty line
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    /**
     * Adds an album to the collection based on input tokens.
     * Validates the artist's date of birth and the album's release date before adding.
     *
     * @param tokens An array of string tokens containing album details.
     */
    private void addAlbum(String[] tokens) {
        String title = tokens[1];
        String artistName = tokens[2];
        String artistDobString = tokens[3];
        Date artistDob = stringToDate(artistDobString);
        if (!artistDob.isValid()) {
            System.out.println("Artist DOB: " + artistDobString + " is invalid.");
            return;
        }
        String releasedString = tokens[5];
        Date released = stringToDate(releasedString);
        if (!released.isValid()) {
            System.out.println("Date Released: " + releasedString + " is invalid.");
            return;
        }
        Genre genre = switch (tokens[4].toLowerCase()) {
            case "pop" -> Genre.Pop;
            case "country" -> Genre.Country;
            case "classical" -> Genre.Classical;
            case "jazz" -> Genre.Jazz;
            default -> Genre.Unknown;
        };

        Artist arist = new Artist(artistName, artistDob);
        Album album = new Album(title, arist, genre, released);

        if (collection.add(album)) {
            System.out.println(title + "(" + artistName + ":" + artistDobString + ") added to the collection.");
        } else {
            System.out.println(title + "(" + artistName + ":" + artistDobString + ") is already in the collection.");
        }
    }

    /**
     * Removes an album from the collection based on input tokens.
     * Matches the album by title and artist before attempting to remove.
     *
     * @param tokens An array of string tokens containing album details to remove.
     */
    private void removeAlbum(String[] tokens) {
        String title = tokens[1];
        String artistName = tokens[2];
        String artistDobString = tokens[3];
        Date artistDob = stringToDate(artistDobString);
        Artist artist = new Artist(artistName, artistDob);
        Album album = new Album(title,artist);

        if (collection.remove(album)) {
            System.out.println(title + "(" + artistName + ":" + artistDobString + ") removed from the collection.");
        } else {
            System.out.println(title + "(" + artistName + ":" + artistDobString + ") is not in the collection");
        }


    }

    /**
     * Rates an existing album in the collection.
     * Finds the album by title and artist and applies the specified rating.
     *
     * @param tokens An array of string tokens containing album details and the rating.
     */
    private void rateAlbum(String[] tokens) {
        String title = tokens[1];
        String artistName = tokens[2];
        String artistDobString = tokens[3];
        Date artistDob = stringToDate(tokens[3]);
        int rating = Integer.parseInt(tokens[4]);

        Artist artist = new Artist(artistName, artistDob);
        Album album = new Album(title, artist, null, null);

        int index = collection.find(album);

        if (index == Collection.NOT_FOUND) {
            System.out.println(title + "(" + artistName + ":" + artistDobString + ") is not in the collection");
            return;
        }

        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating, rating scale is 1 to 5.");
            return;
        }
        collection.rate(collection.getAlbum(index), rating); //change
        System.out.println("You rate " + rating + " for " + title + ":" + collection.getAlbum(index).getReleased().toString() + "(" + artistName + ")");
    }

    /**
     * Converts a string representation of a date (mm/dd/yyyy) into a Date object.
     *
     * @param string The string representation of the date.
     * @return A Date object representing the specified date.
     * @throws IllegalArgumentException If the date string is in an invalid format.
     */
    private Date stringToDate(String string) throws IllegalArgumentException {
        String[] tokens = string.split("/");

        int year = Integer.parseInt(tokens[2]);
        int month = Integer.parseInt(tokens[0]);
        int day = Integer.parseInt(tokens[1]);

        return new Date(month, day, year);
    }
}
