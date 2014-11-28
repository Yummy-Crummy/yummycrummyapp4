package thememeteam.com.yummycrummyapp4;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CreateNewAccount extends Activity{

    EditText nameTxt, passwordTxt, emailTxt, birthdayTxt, genderTxt;
    List<Account> AccountsList = new ArrayList<Account>();
    ListView accountListView;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);


        nameTxt = (EditText) findViewById(R.id.name);
        passwordTxt = (EditText) findViewById(R.id.password);
        emailTxt = (EditText) findViewById(R.id.email);
        birthdayTxt = (EditText) findViewById(R.id.bday);
        genderTxt = (EditText) findViewById(R.id.gender);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        accountListView = (ListView) findViewById(R.id.listView);
        dbHandler = new DatabaseHandler(this, null, null, 1);

        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("account");
        tabSpec.setContent(R.id.tabNewAccount);
        tabSpec.setIndicator("New Account");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabAccountList);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);

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





        final Button submitBtn = (Button) findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account(dbHandler.getAccountsCount(),
                        String.valueOf(nameTxt.getText()),
                        String.valueOf(passwordTxt.getText()),
                        String.valueOf(emailTxt.getText()),
                        String.valueOf(birthdayTxt.getText()),
                        String.valueOf(genderTxt.getText()));
                dbHandler.createAccount(account);
                AccountsList.add(account);
                populateList();
                Toast.makeText(getApplicationContext(), nameTxt.getText().toString() + " has been created!",Toast.LENGTH_SHORT).show();

               /* switch(v.getId())
                {
                    case R.id.submitButton:
                        //submitButtonClick();
                        break;
                }
                */
            }

        });



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

        List<Account> addableAccounts = dbHandler.getAllAccounts();
        int accountCount = dbHandler.getAccountsCount();

        for (int i = 0; i < accountCount; i++){
            AccountsList.add(addableAccounts.get(i));
        }
        if (!addableAccounts.isEmpty())
            populateList();

    }

    private class AccountListAdapter extends ArrayAdapter<Account>{
        public AccountListAdapter(){
            super (CreateNewAccount.this, R.layout.listview_item,AccountsList);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater(). inflate(R.layout.listview_item,parent,false);
            Account currentAccount = AccountsList.get(position);

            TextView name = (TextView) view.findViewById(R.id.txtName);
            name.setText(currentAccount.getName());

            TextView password = (TextView) view.findViewById(R.id.txtPassword);
            password.setText(currentAccount.getPassword());

            TextView email = (TextView) view.findViewById(R.id.txtEmail);
            email.setText(currentAccount.getEmail());

            TextView birthday = (TextView) view.findViewById(R.id.txtBirthday);
            birthday.setText(currentAccount.getBirthday());

            TextView gender = (TextView) view.findViewById(R.id.txtGender);
            gender.setText(currentAccount.getGender());

            return view;
        }

    }

    private void backButtonClick()
    {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.Login_Activity"));
    }

    private void submitButtonClick() {
        startActivity(new Intent("thememeteam.com.yummycrummyapp4.Login_Activity"));
    }

    private void populateList(){
        ArrayAdapter<Account> adapter = new AccountListAdapter();
        accountListView.setAdapter(adapter);
    }


    private void addAccount(int id, String name, String password, String email, String birthday, String gender){
        AccountsList.add(new Account(id, name,password,email,birthday,gender));

    }




}
