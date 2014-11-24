package thememeteam.com.yummycrummyapp4;
//weeeeeeeeeeeeeeeeeeeeeeeeeeeee
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateNewAccount extends Activity implements View.OnClickListener {

    EditText nameTxt, passwordTxt, emailTxt, birthdayTxt, genderTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        Button testdbButton = (Button) findViewById(R.id.testidButton);
        testdbButton.setOnClickListener(this);

        nameTxt = (EditText) findViewById(R.id.name);
        passwordTxt = (EditText) findViewById(R.id.password);
        emailTxt = (EditText) findViewById(R.id.email);
        birthdayTxt = (EditText) findViewById(R.id.bday);
        genderTxt = (EditText) findViewById(R.id.gender);

        final Button submitBtn = (Button) findViewById(R.id.submitButton);

        emailTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                submitBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty()); //submit button is enable once email is entered
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void testdbButtonClick()
    {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.TestDB"));



    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.testidButton:
                testdbButtonClick();
                break;
        }
    }


}
