import java.text.DecimalFormat;

/**
 * Represents a music album, including its title, artist, genre, release date, and ratings.
 * Allows for managing album properties and adding ratings. Provides functionality to calculate
 * average ratings and format album details as a string.
 *
 * @author Woogyeom Sim, Aravind Chundu
 */
public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; //a linked list of ratings

    /**
     * Constructs an Album object with specified title, artist, genre, and release date.
     * Initializes ratings to null.
     *
     * @param title The title of the album.
     * @param artist The artist of the album.
     * @param genre The genre of the album.
     * @param released The release date of the album.
     */
    public Album(String title, Artist artist, Genre genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
        this.ratings = null;
    }

    /**
     * Constructs an Album object with specified title and artist.
     * Initializes genre and release date to null, and ratings to null.
     *
     * @param title The title of the album.
     * @param artist The artist of the album.
     */
    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        this.ratings = null;
    }

    /**
     * Returns the title of the album.
     *
     * @return The title of the album.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the album.
     *
     * @param title The new title of the album.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the artist of the album.
     *
     * @return The artist associated with the album.
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the album.
     *
     * @param artist The new artist associated with the album.
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Returns the genre of the album.
     *
     * @return The genre of the album.
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the album.
     *
     * @param genre The new genre of the album.
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Returns the release date of the album.
     *
     * @return The release date of the album.
     */
    public Date getReleased() {
        return released;
    }

    /**
     * Sets the release date of the album.
     *
     * @param released The new release date of the album.
     */
    public void setReleased(Date released) {
        this.released = released;
    }

    /**
     * Returns the ratings linked list for the album.
     *
     * @return The head of the linked list containing the album's ratings.
     */
    public Rating getRatings() {
        return ratings;
    }

    /**
     * Sets the ratings linked list for the album.
     *
     * @param ratings The new head of the linked list of ratings for the album.
     */
    public void setRatings(Rating ratings) {
        this.ratings = ratings;
    }

    /**
     * Adds a rating to the album. Ratings are stored in a linked list.
     *
     * @param star The star rating to add.
     */
    public void rate(int star) {
        Rating newRating = new Rating(star);
        if (ratings == null) {
            ratings = newRating;
        } else {
            Rating curRating = ratings;
            while (curRating.getNext() != null) {
                curRating = curRating.getNext();
            }
            curRating.setNext(newRating);
        }
    } //add a rating to the linked list

    /**
     * Computes the average of all ratings for the album.
     *
     * @return The average rating as a double. Returns 0 if no ratings.
     */
    public double avgRatings() {
        int total = 0;
        int count = 0;

        Rating curRating = ratings;
        while (curRating != null) {
            total += curRating.getStar();
            count++;
            curRating = curRating.getNext();
        }
        if (count == 0)
        {
            return 0;
        }
        return (double) total /count;
    } //compute the average ratings

    /**
     * Checks if this Album is equal to another object based on title and artist.
     *
     * @param obj The object to compare with.
     * @return true if the given object is an Album with the same title and artist.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Album album = (Album) obj;
        return title.equalsIgnoreCase(album.title) && artist.equals(album.artist);
    }

    /**
     * Returns a string representation of the Album, including title, release date,
     * artist, genre, and a summary of ratings.
     *
     * @return A formatted string representing the album.
     */
    @Override
    public String toString() {
        String returnString = "";
        returnString = returnString + "[" + title + "] Released " + released.toString() + " [" + artist.getName() + ":" + artist.getBorn() + "] [" + genre + "] Rating: ";

        int[] ratingCounts = new int[5];
        Rating ptr = ratings;
        String ratingString = "";

        while (ptr != null) {
            ratingCounts[ptr.getStar() - 1]++;
            ptr = ptr.getNext();
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String dfAverage = df.format(avgRatings());

        if (ratingCounts[0] == 0 && ratingCounts[1] == 0 && ratingCounts[2] == 0 && ratingCounts[3] == 0 && ratingCounts[4] == 0) {
            ratingString = "none";
        } else {
            ratingString = "*(" + ratingCounts[0] + ")**(" + ratingCounts[1] + ")***(" + ratingCounts[2] + ")****(" + ratingCounts[3] + ")*****(" + ratingCounts[4] + ")(average rating: " + dfAverage + ")";
        }

        returnString = returnString + ratingString;
        return returnString;
    }
}
