package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private String user;
    private String pass;
    private boolean validCredential;
    private byte[] encryptedPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(this);

        user = String.valueOf(username.getText());
        pass = String.valueOf(password.getText());

    }

    private void encrypted() {


        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            encryptedPass = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkCredentials() {
        validCredential = false;
        AssetManager assetManager = getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("users.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        StringTokenizer st = null;
        try {
            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line, ",");
                String username = st.nextToken();
                String password = st.nextToken();
                if (user.compareTo(username) == 0 && encryptedPass.equals(password)) {
                    validCredential = true;
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validCredential;

    }

    public void onClick(View v) {

        if ((v == login && validCredential == true)) {
            Intent myIntent = new Intent(Login.this, Home.class);
            Login.this.startActivity(myIntent);

        }
        if (v == register) {
            Intent myIntent = new Intent(Login.this, Profile.class);
            Login.this.startActivity(myIntent);
            String login = "L";
            myIntent.putExtra("login", login);
        }
        return;

    }

}


