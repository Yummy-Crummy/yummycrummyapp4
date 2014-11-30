package thememeteam.com.yummycrummyapp4;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ViewEditProfile extends Activity implements View.OnClickListener {
    DatabaseHandler dbHandler;
    List<Profile> ProfileList = new ArrayList<Profile>();
    ListView profileListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_profile);

        Button editButton;
        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(this);

        dbHandler = new DatabaseHandler(this, null, null, 1);
        profileListView = (ListView) findViewById(R.id.listView2);

        if (dbHandler.getProfileCount() != 0)
            ProfileList.addAll(dbHandler.getAllProfiles());

        Toast.makeText(getApplicationContext(),ProfileList.size()+ " profiles in the list",Toast.LENGTH_SHORT).show();
        populateList();
    }
    private class ProfileListAdapter extends ArrayAdapter<Profile>{
        public ProfileListAdapter(){
            super (ViewEditProfile.this, R.layout.profile_listview_item,ProfileList);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater(). inflate(R.layout.profile_listview_item,parent,false);
            Profile currentProfile = ProfileList.get(position);

            TextView name = (TextView) view.findViewById(R.id.addProfileName);
            name.setText(currentProfile.getName());

            TextView birthday = (TextView) view.findViewById(R.id.addProfileBirthday);
            birthday.setText(currentProfile.getBirthday());

            TextView gender = (TextView) view.findViewById(R.id.addProfileGender);
            gender.setText(currentProfile.getGender());

            return view;
        }

    }

    private void populateList(){
        ArrayAdapter<Profile> adapter = new ProfileListAdapter();
        profileListView.setAdapter(adapter);
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
