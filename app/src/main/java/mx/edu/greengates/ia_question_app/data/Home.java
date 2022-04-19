package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import mx.edu.greengates.ia_question_app.R;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private Button math;
    private Button physics;
    private Button profile;
    private Button weak_point;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        username = intent.getStringExtra("user");

        math = findViewById(R.id.btn_math);
        math.setOnClickListener((View.OnClickListener)this);

        physics = findViewById(R.id.btn_physics);
        physics.setOnClickListener((View.OnClickListener)this);


        profile = findViewById(R.id.btn_profile);
        profile.setOnClickListener((View.OnClickListener)this);

        weak_point = findViewById(R.id.btn_weak_point);
        weak_point.setOnClickListener((View.OnClickListener)this);

    }
    public void onClick(View v){

        

        if(v == math) {
            Log.d("Home","Math button");
            Intent myIntent = new Intent(Home.this, Texts_Math.class);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == physics){
            Log.d("Home","Physics button");
            Intent myIntent = new Intent(Home.this, Texts_Physics.class);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }

        if (v== profile) {
            Log.d("Home","Profile button");
            Intent myIntent = new Intent(Home.this, Profile.class);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }if (v== weak_point) {
            Log.d("Home","Weak button");
            Intent myIntent = new Intent(Home.this, Review.class);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
    }
}