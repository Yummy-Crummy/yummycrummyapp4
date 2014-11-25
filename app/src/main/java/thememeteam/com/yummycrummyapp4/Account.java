package thememeteam.com.yummycrummyapp4;

/**
 * Created by Cassidy on 11/24/2014.
 */
public class Account {

    private String _name, _password, _email, _birthday, _gender;

    public Account (String name, String password, String email, String birthday, String gender) {

        _name = name;
        _password = password;
        _email = email;
        _birthday = birthday;
        _gender = gender;

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
