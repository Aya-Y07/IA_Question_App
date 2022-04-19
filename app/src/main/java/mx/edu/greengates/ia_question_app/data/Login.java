
package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.StringTokenizer;

import mx.edu.greengates.ia_question_app.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);

        // This button will trigger the Login function
        // 1) Take credentials
        // 2) Check against storage (users.csv)
        // 3) If credential fail request registration
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        // This button will trigger the Registration function
        // 1) Request new credentials
        // 2) Add credentials to login file
        // 3) Return to login
        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(this);

    }

    /**
     * This function returns a SHA-1 encrypted HEX string from a given password
     * @param pass the password to convert into SHA-1
     * @return the encrypted password to validate
     */
    private String encrypted(String pass) {
        String encryptedPass = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass.getBytes(StandardCharsets.UTF_8));
            encryptedPass = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPass;
    }

    /**
     * This function should check credentials against the storage
     * If credentials exists and correct return true
     * Else return false
     * @return boolean valid credentials
     */
    private boolean checkCredentials(String username, String password) {
        boolean validCredential = false;  // assume credentials are invalid

        BufferedReader buffReader = null;
        try {
            String sCurrentLine;
            File file = new File(this.getFilesDir(), "users.csv");//get user data from users.csv
            FileReader fileReader = new FileReader(file);
            buffReader = new BufferedReader(fileReader);
            while ((sCurrentLine = buffReader.readLine()) != null) {
                String[] credentials = sCurrentLine.split(",");
                String credUserName = credentials[0]; // First item is username
                String credPassword = credentials[1]; // Second item is password
                if(username.equals(credUserName) && password.equals(credPassword) ) {
                    validCredential = true; // we found a match
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffReader != null) buffReader.close(); // close the buffer
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return validCredential;
    }

    public void onClick(View v) {

        if ((v == login)) {
            String user = String.valueOf(username.getText());
            String pass = String.valueOf(password.getText());
            String encryptedPass = encrypted(pass);
            boolean valid = checkCredentials(user, encryptedPass);
            if (valid) {
                Log.d("Login","Home button");
                Intent myIntent = new Intent(Login.this, Home.class);
                Login.this.startActivity(myIntent);
            } else {
                Log.d("Login","Profile button");
                Intent myIntent = new Intent(Login.this, Profile.class);
                String login = "L";
                myIntent.putExtra("login", login);
                myIntent.putExtra("user", user);
                myIntent.putExtra("pass", pass);
                Login.this.startActivity(myIntent);
            }

        }
        if (v == register) {
            Intent myIntent = new Intent(Login.this, Profile.class);
            String login = "L";
            myIntent.putExtra("login", login);
            Login.this.startActivity(myIntent);
        }
        return;
    }

}