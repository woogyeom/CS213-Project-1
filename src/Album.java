public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; //a linked list of ratings

    public Album(String title, Artist artist, Genre genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
    }

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public Rating getRatings() {
        return ratings;
    }

    public void setRatings(Rating ratings) {
        this.ratings = ratings;
    }

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

    @Override
    public String toString() { // Rating Display needs to be fixed.
        String returnString = "";
        returnString = returnString + "[" + title + "] Released " + released.toString() + " [" + artist.getName() + ":" + artist.getBorn() + "] [" + genre + "] Rating: ";

        int[] ratingCounts = new int[5];
        Rating ptr = ratings;
        String ratingString = "";

        while (ptr != null) {
            ratingCounts[ptr.getStar() - 1]++;
            ptr = ptr.getNext();
        }

        if (ratingCounts[0] == 0 && ratingCounts[1] == 0 && ratingCounts[2] == 0 && ratingCounts[3] == 0 && ratingCounts[4] == 0) {
            ratingString = "none";
        } else {
            ratingString = "*(" + ratingCounts[0] + ")**(" + ratingCounts[1] + ")***(" + ratingCounts[2] + ")****(" + ratingCounts[3] + ")*****(" + ratingCounts[4] + ")(average rating: " + avgRatings() + ")";
        }

        returnString = returnString + ratingString;
        return returnString;
    }
}
