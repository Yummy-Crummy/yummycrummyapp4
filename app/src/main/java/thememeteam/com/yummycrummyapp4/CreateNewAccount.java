package thememeteam.com.yummycrummyapp4;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CreateNewAccount extends Activity{

    EditText nameTxt, passwordTxt, confirmPassTxt, emailTxt, birthdayTxt, genderTxt;
  //  List<Account> AccountsList = new ArrayList<Account>();
  //  ListView accountListView;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        nameTxt = (EditText) findViewById(R.id.name);
        passwordTxt = (EditText) findViewById(R.id.Password);
        confirmPassTxt = (EditText) findViewById(R.id.confirmPassword);
        emailTxt = (EditText) findViewById(R.id.email);
        birthdayTxt = (EditText) findViewById(R.id.bday);
        genderTxt = (EditText) findViewById(R.id.genderField);
     // accountListView = (ListView) findViewById(R.id.listView);
        dbHandler = new DatabaseHandler(this, null, null, 1);


        final Button backButton = (Button) findViewById(R.id.btnBack);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                switch(view.getId())
                {
                    case R.id.btnBack:
                        backButtonClick();
                        break;
                }
            }
        });





        final Button submitBtn = (Button) findViewById(R.id.btnSubmit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account(dbHandler.getAccountsCount(),
                        String.valueOf(nameTxt.getText()),
                        String.valueOf(passwordTxt.getText()),
                        String.valueOf(emailTxt.getText()),
                        String.valueOf(birthdayTxt.getText()),
                        String.valueOf(genderTxt.getText()));
                if(String.valueOf(passwordTxt.getText()).equals(String.valueOf(confirmPassTxt.getText())) ) {

                    if (dbHandler.testDuplicateAccount(account.getName()) == null) {
                        dbHandler.createAccount(account);
                        Toast.makeText(getApplicationContext(), String.valueOf(nameTxt.getText()) + " has been created!", Toast.LENGTH_SHORT).show();
                        submitButtonClick();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), String.valueOf(nameTxt.getText()) + " already exists. Please use a different name.", Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please make your passwords match.", Toast.LENGTH_SHORT).show();
                }
            }

        });



        emailTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                submitBtn.setEnabled(String.valueOf(nameTxt.getText()).trim().length() > 0); //submit button is enable once email is entered
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       // if (dbHandler.getAccountsCount() != 0)
       //     AccountsList.addAll(dbHandler.getAllAccounts());

     //   populateList();

    }

/*

    private class AccountListAdapter extends ArrayAdapter<Account>{
        public AccountListAdapter(){
            super (CreateNewAccount.this, R.layout.listview_item,AccountsList);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater(). inflate(R.layout.listview_item,parent,false);
            Account currentAccount = AccountsList.get(position);

            TextView name = (TextView) view.findViewById(R.id.addProfileName);
            name.setText(currentAccount.getName());

            TextView password = (TextView) view.findViewById(R.id.txtPassword);
            password.setText(currentAccount.getPassword());

            TextView email = (TextView) view.findViewById(R.id.txtEmail);
            email.setText(currentAccount.getEmail());

            TextView birthday = (TextView) view.findViewById(R.id.addProfileBirthday);
            birthday.setText(currentAccount.getBirthday());

            TextView gender = (TextView) view.findViewById(R.id.addProfileGender);
            gender.setText(currentAccount.getGender());

            return view;
        }

    }
 */

 /*   private boolean accountExists(Account account){
        String name = account.getName();
        int accountCount = AccountsList.size();

        for (int i = 0; i < accountCount; i++){
            if (name.compareToIgnoreCase(AccountsList.get(i).getName()) == 0)
                return true;
        }
        return false;
    }
    */

    private void backButtonClick()
    {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.Login_Activity"));
    }

    private void submitButtonClick() {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.Login_Activity"));
    }

  /*  private void populateList(){
        ArrayAdapter<Account> adapter = new AccountListAdapter();
        accountListView.setAdapter(adapter);
    }


    private void addAccount(int id, String name, String password, String email, String birthday, String gender){
        AccountsList.add(new Account(id, name,password,email,birthday,gender));

    }
    */
}
