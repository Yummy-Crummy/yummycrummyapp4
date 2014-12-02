package thememeteam.com.yummycrummyapp4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class AddProfile extends Activity
{


   EditText profileName, profileBday, profileGender;
   ImageView contactImage;
   DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        dbHandler = new DatabaseHandler(this,null,null,1);

        profileName = (EditText) findViewById(R.id.firstName);
        profileBday = (EditText) findViewById(R.id.bday);
        profileGender = (EditText) findViewById(R.id.genderField);
        contactImage = (ImageView) findViewById(R.id.imageView);

        final Button backButton = (Button) findViewById(R.id.btnSubmit);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("thememeteam.com.yummycrummyapp4.HomeScreen"));
            }
        });

        final Button submitButton = (Button) findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Profile profile = new Profile(dbHandler.getMyAccount(),
                        dbHandler.getProfileCount(),
                        String.valueOf(profileName.getText()),
                        String.valueOf(profileBday.getText()),
                        String.valueOf(profileGender.getText()));
                if (dbHandler.getProfile(dbHandler.getMyAccount(), String.valueOf(profileName.getText()),dbHandler.getProfileCount(),0) == null) {
                    dbHandler.createProfile(profile);
                    Toast.makeText(getApplicationContext(), String.valueOf(profileName.getText()) + " has been created!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent("thememeteam.com.yummycrummyapp4.HomeScreen"));
                }
                else {
                    Toast.makeText(getApplicationContext(), String.valueOf(profileName.getText()) + " already exists. Please use a different name.", Toast.LENGTH_SHORT).show();
                }
            }

        });//end setOnClickListener

        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), 1);
            }
        });
    }//end OnCreate



    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == RESULT_OK){
            contactImage.setImageURI(data.getData());
        }
    }

} //end class
