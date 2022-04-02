package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.WriteIntoUserCSV;


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

            Intent intent = getIntent();
            confirm = intent.getStringExtra("login");


            submitBtn = (Button) findViewById(R.id.btn_profile_submit);
            profile_username = (EditText) findViewById(R.id.profile_username);
            profile_password = (EditText) findViewById(R.id.profile_password);

            submitBtn.setOnClickListener(this);


            username = profile_username.getText().toString();
            password = profile_password.getText().toString();

        }

    public void encryptedpasswid(){
            try{
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                b = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            }catch(Exception e) {
                e.printStackTrace();
            }
    }
    public void onClick(View view) {



            if (view == submitBtn){

                try {

                    FileWriter fw = new FileWriter("users.csv", true);
                    PrintWriter pw = new PrintWriter(new BufferedWriter(fw));


                    pw.print(username);
                    pw.print(",");
                    pw.print(b);

                    pw.close();


                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Finish");
                builder.setMessage("Your account is saved. please press go ack");
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