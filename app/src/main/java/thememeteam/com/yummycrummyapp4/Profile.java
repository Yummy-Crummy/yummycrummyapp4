package thememeteam.com.yummycrummyapp4;

/**
 * Created by Cassidy on 11/28/2014.
 */
public class Profile {

    private String _name, _birthday, _gender;
    private int _profileID, _accountID;

    public Profile(){

    }

    public Profile (int aID, int pID, String name, String birthday, String gender) {
        _accountID = aID;
        _profileID = pID;
        _name = name;
        _birthday = birthday;
        _gender = gender;

    }

    public int getAccountID(){
        return _accountID;
    }

    public void setAccountID(int id){
        _accountID = id;
    }

    public int getProfileID(){ return _profileID;}

    public void setProfileID(int id) { _profileID = id;}

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getBirthday(){
        return _birthday;
    }

    public void setBirthday(String birthday){
        _birthday = birthday;
    }

    public String getGender(){
        return _gender;
    }

    public void setGender(String gender){
        _gender = gender;
    }

}
