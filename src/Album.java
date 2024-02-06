public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; //a linked list of ratings

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
        while (curRating.getNext() != null) {
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
}
