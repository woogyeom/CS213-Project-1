public class Collection {
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH = 4;
    public static final int NOT_FOUND = -1;
    private Album[] albums;
    private int size;

    public Collection() {
        albums = new Album[INITIAL_CAPACITY];
        size = 0;
    }

    public Album getAlbum(int i){
        return albums[i];
    }

    public int find(Album album) {
        for (int i = 0; i < size; i++) {
            if (albums[i].equals(album)) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    private void grow() {
        Album[] newAlbums = new Album[albums.length + GROWTH];
        for (int i = 0; i < size; i++) {
            newAlbums[i] = albums[i];
        }
        albums = newAlbums;
    }
    public boolean contains(Album album) {
        return find(album) != NOT_FOUND;
    }
    public boolean add(Album album) {
        if (contains(album)) {
            //System.out.println("Debug False");
            return false;
        } // if there already is
        if (size == albums.length) {
            grow();
        } // if full, grow
        albums[size] = album;
        size++;
        return true;
    }
    public boolean remove(Album album) {
        //int index = find(album);   -not using find() because it compares entire album object, and we only get the title, artistName, and artistDob
        int index = NOT_FOUND;
        for (int i = 0; i < size; i++){
            if (albums[i].getTitle().equalsIgnoreCase(album.getTitle())) {
                if (albums[i].getArtist().getName().equalsIgnoreCase(album.getArtist().getName())) {
                    if (albums[i].getArtist().getBorn().compareTo(album.getArtist().getBorn()) == 0) {
                        index = i;
                        break;
                    }
                }
            }
        }

        if (index == NOT_FOUND) {
            return false;
        } // if there isn't
        for (int i = index; i < size - 1; i++) {
            albums[i] = albums[i + 1];
        } // shifting
        size--;
        albums[size] = null;
        return true;
    }
    public void rate(Album album, int rating) {
        album.rate(rating);
    }
    public void printByDate() {
        if (size == 0) {
            System.out.println("Collection is empty!");
        } else {
            sortByDate();
            System.out.println("* Collection sorted by Released Date/Title *");
            for (Album album : albums) {
                if (album != null) {
                    System.out.println(album.toString());
                }
            }
            System.out.println("* end of list *");
        }
    }
    public void printByGenre() {
        if (size == 0) {
            System.out.println("Collection is empty!");
        } else {
            sortByGenre();
            System.out.println("* Collection sorted by Genre/Artist *");
            for (Album album : albums) {
                if (album != null) {
                    System.out.println(album.toString());
                }
            }
            System.out.println("* end of list *");
        }
    }
    public void printByRating() {
        if (size == 0) {
            System.out.println("Collection is empty!");
        } else {
            sortByRating();
            System.out.println("* Collection sorted by Rating/Title *");
            for (int i = size - 1; i >= 0; i--) {
                if (albums[i] != null) {
                    System.out.println(albums[i].toString());
                }
            }
            System.out.println("* end of list *");
        }
    }
    private void sortByDate() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (albums[j].getReleased().compareTo(albums[j + 1].getReleased()) > 0) {
                    // if it's released later, swap their position
                    Album temp = albums[j];
                    albums[j] = albums[j + 1];
                    albums[j + 1] = temp;
                } else if (albums[j].getReleased().compareTo(albums[j + 1].getReleased()) == 0) {
                    // if they're released on the same day, compare the title
                    if (albums[j].getTitle().compareToIgnoreCase(albums[j + 1].getTitle()) > 0) {
                        // Alphabetical order
                        Album temp = albums[j];
                        albums[j] = albums[j + 1];
                        albums[j + 1] = temp;
                    }
                }
            }
        }
    }
    private void sortByGenre() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (albums[j].getGenre().compareTo(albums[minIndex].getGenre()) < 0) {
                    minIndex = j;
                } else if (albums[j].getGenre().compareTo(albums[minIndex].getGenre()) == 0) {
                    if (albums[j].getArtist().getName().compareTo(albums[minIndex].getArtist().getName()) < 0) {
                        minIndex = j;
                    }
                }
            }

            Album temp = albums[minIndex];
            albums[minIndex] = albums[i];
            albums[i] = temp;
        }
    }
    private void sortByRating() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (albums[j].avgRatings() < albums[minIndex].avgRatings()) {
                    minIndex = j;
                } else if (albums[j].avgRatings() == albums[minIndex].avgRatings()) {
                    if (albums[j].getTitle().compareTo(albums[minIndex].getTitle()) < 0) {
                        minIndex = j;
                    }
                }
            }

            Album temp = albums[minIndex];
            albums[minIndex] = albums[i];
            albums[i] = temp;
        }
    }
}
