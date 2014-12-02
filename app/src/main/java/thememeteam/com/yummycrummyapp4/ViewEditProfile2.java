package thememeteam.com.yummycrummyapp4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ViewEditProfile2 extends Activity implements View.OnClickListener {
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

        Button backButton;
        backButton = (Button) findViewById(R.id.btnBack);
        backButton.setOnClickListener(this);

        Button deleteButton;
        deleteButton = (Button) findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(this);

        nameTxt.setText(currentProfile.getName());
        genderTxt.setText(currentProfile.getGender());
        birthdayTxt.setText(currentProfile.getBirthday());


        final Button submitBtn = (Button) findViewById(R.id.submitChangesButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = new Profile(currentProfile.getAccountID(),
                        currentProfile.getProfileID(),
                        String.valueOf(nameTxt.getText()),
                        String.valueOf(birthdayTxt.getText()),
                        String.valueOf(genderTxt.getText()));
                dbHandler.updateProfile(profile);
                Toast.makeText(getApplicationContext(), dbHandler.getProfileCount() + " profiles in the database!", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(), String.valueOf(genderTxt.getText()) + " has been edited!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent("thememeteam.com.yummycrummyapp4.ViewEditProfile"));
                }


        });


    }

    private void backButtonClick()
    {

        startActivity(new Intent("thememeteam.com.yummycrummyapp4.ViewEditProfile"));
    }

    //This function works as long as you only delete the last profile in the database...
    //Otherwise it goes bananas
    private void deleteButtonClick() {
        int deletedProfileID = currentProfile.getProfileID();
        dbHandler.deleteProfile(deletedProfileID);
        Toast.makeText(getApplicationContext(), dbHandler.getProfileCount() + " profiles in the database!", Toast.LENGTH_SHORT).show();
        //reset profileNumber?
        Profile nextProfile;
        if (dbHandler.getProfileCount() != 0)
        {
            for (int i = deletedProfileID + 1; i < dbHandler.getProfileCount() + 1; i++) {
                nextProfile = dbHandler.getProfile(dbHandler.getMyAccount(), "", i, 1);
                dbHandler.updateProfileID(nextProfile, i - 1);
            }
        }
            startActivity(new Intent("thememeteam.com.yummycrummyapp4.ViewEditProfile"));

    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnBack:
                backButtonClick();
                break;
            case R.id.btnDelete:
                deleteButtonClick();
                break;
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */

}
