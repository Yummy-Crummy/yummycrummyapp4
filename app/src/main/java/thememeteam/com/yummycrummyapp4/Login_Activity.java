package thememeteam.com.yummycrummyapp4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Login_Activity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Button createNewAccountButton;
        createNewAccountButton = (Button) findViewById(R.id.createNewAccountButton);
        createNewAccountButton.setOnClickListener(this);
    }

    private void createNewAccountButtonClick()
    {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.CreateNewAccount"));
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.createNewAccountButton:
                createNewAccountButtonClick();
                break;
        }
    }


}
