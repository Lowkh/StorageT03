package sg.edu.np.week7t03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String GLOBAL_PREFS = "MyPrefs";
    public String MY_USERNAME = "MyUserName";
    public String MY_PASSWORD = "MyPassword";

    TextView createUser;
    Button loginButton;
   // SharedPreferences sharedPreferences;
    DBHandler dbHandler = new DBHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createUser = findViewById(R.id.newUser);

        createUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return false;
            }
        });

        loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsername = findViewById(R.id.userLogin);
                EditText etPassword = findViewById(R.id.UserPassword);
                //compare username and password???
                if(isValidCredentials(etUsername.getText().toString(), etPassword.getText().toString()))
                {
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Valid Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidCredentials(String username, String password)
    {
       /* sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String sharedUsername = sharedPreferences.getString(MY_USERNAME, "");
        String sharedPassword = sharedPreferences.getString(MY_PASSWORD, "");

        if(sharedUsername.equals(username) && sharedPassword.equals(password)){
            return true;
        }
        return false; */

        UserData dbData = dbHandler.findUser(username);
        if(dbData == null)
        {
            Toast.makeText(this, "User does not exist. Please Signup.", Toast.LENGTH_SHORT).show();

        }
        else
        {
            if(dbData.getUsername().equals(username) && dbData.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}