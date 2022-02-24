
package group1.csc311hw1;

public class VideoGame {
    
    private String title;
    private double price;
    private String esrb;
    
    
    /**
     * default constructor, creates default values for a video game 
     */
    public VideoGame(){//default constructor
        this.title = "default";//sets all three member variables to default values
        this.price = 0.00;
        this.esrb = "n/a";
    }
    /**
     * overloaded constructor, accepts two strings and a double for title, price, and ESRB rate
     * @param t
     * @param p
     * @param r 
     */
    public VideoGame(String t, double p, String r){//overloaded constructor 
        this.title = t;
        this.price = p;
        this.esrb = r;
    }
    /**
     * sets the title of a video game
     * @param t 
     */
    public void setTitle(String t){//setters, they set the values 
        this.title = t;
    }
    /**
     * sets the price of a video game
     * @param p 
     */
    public void setPrice(double p){
        this.price = p;
    }
    /**
     * sets the esrb rating of a videogame
     * @param r 
     */
    public void setEsrbRating(String r){
        this.esrb = r;
    }
    /**
     * returns the title of a videogame
     * @return 
     */
    public String getTitle(){//getters, they return values
        return this.title;
    }
    /**
     * returns the price of a videogame
     * @return 
     */
    public double getPrice(){
        return this.price;
    }
    /**
     * returns the rating of a videogame 
     * @return 
     */
    public String getRating(){
        return this.esrb;
    }
    /**
     * toString method to return the title, price, and esrb rating for the listview
     * @return 
     */
    public String toString(){
        String str = title + ", "+ price + ", " + esrb;
        return str;
    }
}
