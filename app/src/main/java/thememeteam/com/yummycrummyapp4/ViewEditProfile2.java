package thememeteam.com.yummycrummyapp4;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;


public class ViewEditProfile2 extends ActionBarActivity {
    EditText nameTxt, birthdayTxt, genderTxt;
    Profile currentProfile;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_profile2);


        nameTxt = (EditText) findViewById(R.id.nameField);
        birthdayTxt = (EditText) findViewById(R.id.bdayField);
        genderTxt = (EditText) findViewById(R.id.genderField);
        dbHandler = new DatabaseHandler(this, null, null, 1);
        currentProfile = dbHandler.getProfile(dbHandler.getMyAccount(),"",dbHandler.getMyProfile(),1);

        nameTxt.setText(currentProfile.getName());
        genderTxt.setText(currentProfile.getGender());
        birthdayTxt.setText(currentProfile.getBirthday());


    }




    /**
     * A placeholder fragment containing a simple view.
     */

}
