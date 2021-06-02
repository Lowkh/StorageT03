package sg.edu.np.week7t03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    //SharedPreferences sharedPreferences;
    DBHandler dbHandler = new DBHandler(this, null , null, 1);
    public String GLOBAL_PREFS = "MyPrefs";
    public String MY_USERNAME = "MyUserName";
    public String MY_PASSWORD = "MyPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText etUsername = findViewById(R.id.createUsername);
        EditText etPassword = findViewById(R.id.createPassword);

        Button createButton = findViewById(R.id.button2);
        Button cancelButton = findViewById(R.id.button3);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MY_USERNAME, etUsername.getText().toString());
                editor.putString(MY_PASSWORD, etPassword.getText().toString());
                editor.apply();

                Toast.makeText(MainActivity2.this, "New user Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);*/
                UserData dbData = dbHandler.findUser(etUsername.getText().toString());
                if(dbData == null)
                {
                    String dbUsername = etUsername.getText().toString();
                    String dbPassword = etPassword.getText().toString();
                    UserData dbUserData = new UserData();
                    dbUserData.setUsername(dbUsername);
                    dbUserData.setPassword(dbPassword);
                    dbHandler.addUser(dbUserData);
                    Toast.makeText(MainActivity2.this, "New User Created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity2.this, "User already exist.\nPLease try again.", Toast.LENGTH_SHORT).show();

                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}