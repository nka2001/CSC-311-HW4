
package group1.csc311hw1;

public class VideoGame {
    
    private String title;
    private double price;
    private String esrb;
    
    public VideoGame(){//default constructor
        this.title = "default";//sets all three member variables to default values
        this.price = 0.00;
        this.esrb = "n/a";
    }
    public VideoGame(String t, double p, String r){//overloaded constructor 
        this.title = t;
        this.price = p;
        this.esrb = r;
    }
    public void setTitle(String t){//setters, they set the values 
        this.title = t;
    }
    public void setPrice(double p){
        this.price = p;
    }
    public void setEsrbRating(String r){
        this.esrb = r;
    }
    public String getTitle(){//getters, they return values
        return this.title;
    }
    public double getPrice(){
        return this.price;
    }
    public String getRating(){
        return this.esrb;
    }
    public String toString(){
        String str = title + ", "+ price + ", " + esrb;
        return str;
    }
}
