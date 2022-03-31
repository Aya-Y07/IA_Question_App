package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

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
        private EditText profile_surname;
        private EditText profile_first_name;
        private EditText profile_email_address;
        private EditText profile_phone_number;
        private Button submitBtn;
        private Button btn_go_back;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);


            submitBtn = (Button) findViewById(R.id.btn_profile_submit);
            profile_username = (EditText) findViewById(R.id.profile_username);
            profile_password = (EditText) findViewById(R.id.profile_password);
            profile_surname = (EditText) findViewById(R.id.profile_surname);
            profile_first_name = (EditText) findViewById(R.id.profile_firstname);
            profile_email_address = (EditText) findViewById(R.id.profile_email_address);
            profile_phone_number = (EditText) findViewById(R.id.prodfile_phone_number);
            btn_go_back = (Button) findViewById(R.id.btn_go_back);

            submitBtn.setOnClickListener(this);
            btn_go_back.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
            if (view == submitBtn){
                String username = profile_username.getText().toString();
                String firstname = profile_first_name.getText().toString();
                String surname = profile_surname.getText().toString();
                String password = profile_password.getText().toString();
                String email = profile_email_address.getText().toString();
                String phoneNumber = profile_phone_number.getText().toString();


                String[] userData = {username,firstname,surname,password,email,phoneNumber};
                WriteIntoUserCSV writer = new WriteIntoUserCSV(this,"users.csv");

                try{
                    writer.writeUserDataCSV("users.csv",userData);
                }catch (IOException e) {

                    e.printStackTrace();

                }



            }if (view == btn_go_back){
                Intent myIntent = new Intent(Profile.this, Home.class);
                Profile.this.startActivity(myIntent);
        }



    }





}