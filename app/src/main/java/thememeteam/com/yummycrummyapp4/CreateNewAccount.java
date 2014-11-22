package thememeteam.com.yummycrummyapp4;
//weeeeeeeeeeeeeeeeeeeeeeeeeeeee
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class CreateNewAccount extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        Button testdbButton = (Button) findViewById(R.id.testidButton);
        testdbButton.setOnClickListener(this);
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
