public class Artist implements Comparable<Artist> {
    private String name;
    private Date born;

    public Artist(String name, Date born) {
        this.name = name;
        this.born = born;
    }
    public int compareTo(Artist art) {
        if (this.name.compareToIgnoreCase(art.name) != 0) {
            return this.name.compareToIgnoreCase(art.name);
        }
        return this.born.compareTo(art.born);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
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
        // should it just be "Taylor Swift, 12/13/1989"?
        return this.name + ", " + this.born.toString();
    }
}
