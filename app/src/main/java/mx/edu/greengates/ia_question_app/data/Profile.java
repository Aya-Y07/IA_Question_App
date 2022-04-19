package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import mx.edu.greengates.ia_question_app.R;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private EditText profile_username;
    private EditText profile_password;
    private Button submitBtn;
    private String confirm = "";
    private String username;
    private String password;
    private byte[] b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = "Username";
        password = "Password";

        Intent intent = getIntent();
        confirm = intent.getStringExtra("login");
        if (intent.hasExtra("user")) {
            username = intent.getStringExtra("user");
        }
        if (intent.hasExtra("pass")){
            password = intent.getStringExtra("pass");
        }

        submitBtn = (Button) findViewById(R.id.btn_profile_submit);
        profile_username = (EditText) findViewById(R.id.profile_username);
        profile_username.setText(username);
        profile_password = (EditText) findViewById(R.id.profile_password);
        profile_password.setText(password);

        submitBtn.setOnClickListener(this);
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

    public void onClick(View view) {

        if (view == submitBtn){

            username = profile_username.getText().toString();
            password = profile_password.getText().toString();
            String encryptedPass = encrypted(password);
            try {
                File file = new File(this.getFilesDir(), "users.csv");
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);

                StringBuffer sb = new StringBuffer();
                sb.append(username);
                sb.append(",");
                sb.append(encryptedPass);
                String credential = sb.toString();
                Log.d("Profile", credential);

                bw.write(credential);
                bw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Finish");
            builder.setMessage("Your account is saved.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (confirm.compareTo("L") == 0) {
                        Intent myIntent = new Intent(Profile.this, Login.class);
                        Profile.this.startActivity(myIntent);
                    } else {
                        Intent myIntent = new Intent(Profile.this, Home.class);
                        Profile.this.startActivity(myIntent);
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();
        }

    }

}
