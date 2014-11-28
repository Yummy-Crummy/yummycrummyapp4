package thememeteam.com.yummycrummyapp4;

/**
 * Created by Cassidy on 11/24/2014.
 */
public class Account {

    private String _name, _password, _email, _birthday, _gender;
    private int _id;

    public Account(){

    }

    public Account (int id, String name, String password, String email, String birthday, String gender) {
        _id = id;
        _name = name;
        _password = password;
        _email = email;
        _birthday = birthday;
        _gender = gender;

    }

    public int getId(){
        return _id;
    }

    public void setId(int id){
       _id = id;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getPassword(){
        return _password;
    }

    public void setPassword(String password){
        _password = password;
    }

    public String getEmail(){
        return _email;
    }

    public void setEmail(String email){
        _email = email;
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
