package thememeteam.com.yummycrummyapp4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ViewEditProfile extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_profile);

        Button editButton;
        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
    }

    private void editButtonClick()
    {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.ViewEditProfile2"));
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.editButton:
                editButtonClick();
                break;
        }
    }
}
