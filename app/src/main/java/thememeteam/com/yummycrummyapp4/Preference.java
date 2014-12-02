package thememeteam.com.yummycrummyapp4;

/**
 * Created by Cassidy on 12/1/2014.
 */
public class Preference {

    private String _restaurant, _foodItem, _address, _comments;
    private int _profileID,_prefID, _rating;

    public Preference(){

    }

    public Preference (int profileID, int prefID, String restaurant, String address, String foodItem, int rating, String comments) {
        _restaurant = restaurant;
        _foodItem = foodItem;
        _address = address;
        _comments = comments;
        _profileID = profileID;
        _prefID = prefID;
        _rating = rating;


    }

    public String getRestaurant(){return _restaurant;}

    public void setRestaurant(String res){_restaurant = res;}

    public String getFoodItem(){return _foodItem;}

    public void setFoodItem(String food){_foodItem = food;}

    public String getAddress(){return _address;}

    public void setAddress(String address){_address = address;}

    public String getComments(){return _comments;}

    public void setComments(String comment){_comments = comment;}

    public int getProfileID(){return _profileID;}

    public void setProfileID(int id){_profileID = id;}

    public int getPrefID(){return _prefID;}

    public void setPrefID(int id){_prefID = id;}

    public int getRating(){return _rating;}

    public void setRating(int rating){_rating = rating;}


}
