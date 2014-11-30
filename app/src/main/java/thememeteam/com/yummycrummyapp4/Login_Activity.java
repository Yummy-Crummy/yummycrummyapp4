package thememeteam.com.yummycrummyapp4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login_Activity extends Activity implements View.OnClickListener {
    DatabaseHandler dbHandler;
    EditText usernameTxt, userPasswordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        usernameTxt = (EditText) findViewById(R.id.Username);
        userPasswordTxt = (EditText) findViewById(R.id.Password);

        Button createNewAccountButton;
        createNewAccountButton = (Button) findViewById(R.id.createNewAccountButton);
        createNewAccountButton.setOnClickListener(this);

        Button submitButton;
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        dbHandler = new DatabaseHandler(this, null, null, 1);
    }

    private void createNewAccountButtonClick()
    {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.CreateNewAccount"));
    }

    private void submitButtonClick()
    {
        Boolean validated = validateUserAccount(usernameTxt.getText(), userPasswordTxt.getText());
        if (validated == true){
            //start new intent with the main page
            startActivity(new Intent("thememeteam.com.yummycrummyapp4.HomeScreen"));
        }

    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.createNewAccountButton:
                createNewAccountButtonClick();
                break;
            case R.id.submitButton:
                submitButtonClick();
                break;
        }
    }


    private Boolean validateUserAccount(android.text.Editable username, android.text.Editable password){
        Account account = dbHandler.getAccount(String.valueOf(username), String.valueOf(password));
        if (account != null) //if they have already made an account with that username and password
        {
            dbHandler.setMyAccount(account.getId());
            Toast.makeText(getApplicationContext(), dbHandler.myAccount + "Login is valid", Toast.LENGTH_SHORT).show();
          //  Toast.makeText(getApplicationContext(), password + " is equal to " + account.getPassword(), Toast.LENGTH_SHORT).show();
            return true;
        }

        else{ //If they have not created an account with that username
            Toast.makeText(getApplicationContext(), " Username or Password is incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
