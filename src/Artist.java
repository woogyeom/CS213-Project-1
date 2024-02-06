public class Artist {
    private String name;
    private Date born;

    public Artist(String name, Date born) {
        this.name = name;
        this.born = born;
    }
    public int compareTo(Artist art) {

        return 0;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Artist artist = (Artist) obj;
        return name.equalsIgnoreCase(artist.name) && born.equals(artist.born);
    } // checks if the name and the born date is same
    @Override
    public String toString() {
        // I don't exactly know what this method is supposed to do
        // Is it just supposed to list the name and the born date in String?
        // Unlike the toString method on the album class, the format is not give for this one
        return " ";
    }
}
