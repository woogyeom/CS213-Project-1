/**
 * Manages artist information including name and birth date. Supports comparison based on name and date for sorting.
 * Overrides equals and toString for equality checks and text representation.
 *
 * @author Woogyeom Sim, Aravind Chundu
 */
public class Artist implements Comparable<Artist> {
    private String name;
    private Date born;

    /**
     * Constructs an Artist object with a name and birth date.
     *
     * @param name The artist's name.
     * @param born The artist's date of birth.
     */
    public Artist(String name, Date born) {
        this.name = name;
        this.born = born;
    }

    /**
     * Compares this Artist object with another Artist object to determine their ordering
     * based on name (case-insensitive) and birth date.
     *
     * @param art The Artist object to be compared.
     * @return A negative integer, zero, or a positive integer as this Artist is less than,
     * equal to, or greater than the specified Artist.
     */
    public int compareTo(Artist art) {
        if (this.name.compareToIgnoreCase(art.name) != 0) {
            return this.name.compareToIgnoreCase(art.name);
        }
        return this.born.compareTo(art.born);
    }

    /**
     * Returns the artist's name.
     *
     * @return The name of the artist.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the artist's name.
     *
     * @param name The new name of the artist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the artist's date of birth.
     *
     * @return The birth date of the artist.
     */
    public Date getBorn() {
        return born;
    }

    /**
     * Sets the artist's date of birth.
     *
     * @param born The new birth date of the artist.
     */
    public void setBorn(Date born) {
        this.born = born;
    }

    /**
     * Indicates whether some other object is "equal to" this one,
     * comparing by the artist's name (case-insensitive) and birth date.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Artist artist = (Artist) obj;
        if (name.equalsIgnoreCase(artist.name) && born.compareTo(artist.born) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the Artist object.
     *
     * @return A string that represents the artist's name and birth date.
     */
    @Override
    public String toString() {
        return this.name + ":" + this.born.toString();
    }

    /**
     * Main method for demonstrating the functionality of the Artist class.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Artist artistTest1 = new Artist("Jane Doe", new Date(1, 1, 1990));
        Artist artistTest2 = new Artist("John Doe", new Date(1, 1, 1990));

        Artist artistTest3 = new Artist("Jane Doe", new Date(12, 31, 1989));
        Artist artistTest4 = new Artist("Jane Doe", new Date(1, 1, 1990));

        Artist artistTest5 = new Artist("Zane Doe", new Date(1, 1, 1990));
        Artist artistTest6 = new Artist("Alan Doe", new Date(1, 1, 1990));

        Artist artistTest7 = new Artist("Jane Doe", new Date(1, 1, 1991));
        Artist artistTest8 = new Artist("Jane Doe", new Date(12, 31, 1990));

        Artist artistTest9 = new Artist("Jane Doe", new Date(1, 1, 1990));
        Artist artistTest10 = new Artist("Jane Doe", new Date(1, 1, 1990));

        System.out.println(artistTest1.compareTo(artistTest2)); //Return negative integer: artistTest1 is alphabetically before artistTest2
        System.out.println(artistTest3.compareTo(artistTest4)); //Return negative integer: names are equal, but artistTest3 has an earlier date of birth than artistTest4
        System.out.println(artistTest5.compareTo(artistTest6)); //Return positive integer: artistTest5 is alphabetically after artistTest6
        System.out.println(artistTest7.compareTo(artistTest8)); //Return positive integer: names are equal, artistTest7 has a later date of birth than artistTest8
        System.out.println(artistTest9.compareTo(artistTest10)); //Return 0: artistTest9 and artistTest10 are equal (same names, and same date of birth)
    }
}
