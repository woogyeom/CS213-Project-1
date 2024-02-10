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
        if (name.equalsIgnoreCase(artist.name) && born.compareTo(artist.born) == 0) {
            return true;
        } else {
            return false;
        }
    } // checks if the name and the born date is same
    @Override
    public String toString() {
        return this.name + ":" + this.born.toString();
    }

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
