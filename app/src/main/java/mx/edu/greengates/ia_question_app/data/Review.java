package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.edu.greengates.ia_question_app.R;

public class Review extends AppCompatActivity {

    private Button math_weak_points;
    private Button physics_weak_points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        math_weak_points = (Button) findViewById(R.id.btn_review_math);
        math_weak_points.setOnClickListener((View.OnClickListener)this);

        physics_weak_points = (Button) findViewById(R.id.review_physics);
        physics_weak_points.setOnClickListener((View.OnClickListener)this);
    }

    public void onClick(View v) {

        if (v == math_weak_points) {
            Intent myIntent = new Intent(Review.this, Texts_Math.class);
            String subject = "M";
            myIntent.putExtra("Subject", subject);
            startActivity(myIntent);
        }
        if (v == physics_weak_points) {
            Intent myIntent = new Intent(Review.this, Texts_Physics.class);
            String subject = "P";
            myIntent.putExtra("Subject", subject);
            startActivity(myIntent);
        }
    }


}