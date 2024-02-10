public class Rating {
    private int star;
    private Rating next;

    public Rating(int star, Rating next){
        this.star = star;
        this.next = next;
    }
    public Rating(int star){
        this.star = star;
        this.next = null;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star){
        this.star = star;
    }

    public Rating getNext(){
        return next;
    }

    public void setNext(Rating next){
        this.next = next;
    }
}
