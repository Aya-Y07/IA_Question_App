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
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.loginCred;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.profile_username);
        password = (EditText) findViewById(R.id.input_password);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(this);

    }

    private String password(String password){

        String token = "";

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            token = String.format("%040x", new BigInteger(1,digest.digest()));//BingInteger 整数値を格納するためのクラス

        }catch(Exception e){
            e.printStackTrace();
        }
        return token;

    }

    private boolean checkCredentials(String user, String pass){
        boolean validCredential = false;
        String encryptedPass = password(pass);
        ArrayList<loginCred> objList = new ArrayList<loginCred>();
        AssetManager assetManager = getAssets();
        InputStream is = null;
        try{
            is = assetManager.open("users.csv");
        }catch(IOException e){
            e.printStackTrace();
        }
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line ="" ;
        StringTokenizer st = null;
        try{
            while((line = reader.readLine())!=null){
                st = new StringTokenizer(line,",");
                String username = st.nextToken();
                String password = st.nextToken();
                System.out.println(password + " : " + encryptedPass);
                if (user.compareTo(username) == 0 && encryptedPass.compareToIgnoreCase(password) == 0){
                    validCredential = true;
                    break;
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return validCredential;

    }

    public void onClick(View v)
    {
        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());

        if(v == login && checkCredentials(user,pass)){
            Intent myIntent = new Intent(Login.this, Home.class);
            myIntent.putExtra("progress", 50);
            Login.this.startActivity(myIntent);

        }if (v == register){
            Intent myIntent = new Intent(Login.this, Profile.class);
            Login.this.startActivity(myIntent);
            String login = "L";
            myIntent.putExtra("login", login);
    }return;

    }














}