package thememeteam.com.yummycrummyapp4;

/**
 * Created by Cassidy on 11/24/2014.
 */
public class Account {

    private String _name, _password, _email, _birthday, _gender;
    private int _id;

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

    public String getName(){
        return _name;
    }

    public String getPassword(){
        return _password;
    }

    public String getEmail(){
        return _email;
    }

    public String getBirthday(){
        return _birthday;
    }

    public String getGender(){
        return _gender;
    }

}