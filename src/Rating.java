/**
 * Represents a rating for a music album, using a star system where the number of stars indicates the quality.
 * This class also supports a linked list structure to chain multiple ratings together, allowing for the aggregation
 * of ratings for a single album.
 *
 * @author Woogyeom Sim, Aravind Chundu
 */

public class Rating {
    private int star;
    private Rating next;

    /**
     * Constructs a Rating with a specified number of stars and a reference to the next Rating.
     *
     * @param star The number of stars for this rating.
     * @param next The next Rating in the chain.
     */
    public Rating(int star, Rating next){
        this.star = star;
        this.next = next;
    }

    /**
     * Constructs a Rating with a specified number of stars. Initializes this rating as the last in the chain.
     *
     * @param star The number of stars for this rating.
     */
    public Rating(int star){
        this.star = star;
        this.next = null;
    }

    /**
     * Returns the number of stars for this rating.
     *
     * @return The star count.
     */
    public int getStar() {
        return star;
    }

    /**
     * Sets the number of stars for this rating.
     *
     * @param star The new star count.
     */
    public void setStar(int star){
        this.star = star;
    }

    /**
     * Returns the next Rating in the chain.
     *
     * @return The next Rating object or null if this is the last rating.
     */
    public Rating getNext(){
        return next;
    }

    /**
     * Sets the reference to the next Rating in the chain.
     *
     * @param next The Rating that follows this one.
     */
    public void setNext(Rating next){
        this.next = next;
    }
}
