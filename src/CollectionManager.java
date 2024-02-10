import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class CollectionManager {
    private Collection collection;
    public  CollectionManager() {
        this.collection = new Collection();
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader("Project1TestCases.txt"))) {
            String input;
            while ((input = br.readLine()) != null) {
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
                    case "": // empty line
                        break;
                    default:
                        System.out.println("Invalid command!");
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

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
        collection.rate(collection.getAlbum(index), rating); //chaange
        System.out.println("You rate " + rating + " for " + title + ":" + artistDobString + "(" + artistName + ")");
    }
    private Date stringToDate(String string) throws IllegalArgumentException {
        String[] tokens = string.split("/");

        int year = Integer.parseInt(tokens[2]);
        int month = Integer.parseInt(tokens[0]);
        int day = Integer.parseInt(tokens[1]);

        return new Date(month, day, year);
    }
}
