package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import mx.edu.greengates.ia_question_app.R;

public class Texts_Math extends AppCompatActivity implements View.OnClickListener {

    private Button trig;
    private Button alg;
    private Button vec;
    private Button comp;

    private Button ans_trig;
    private Button ans_dif;
    private Button ans_vec;
    private Button ans_comp;

    private String username;
    private String subject = "M";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_math);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        trig = findViewById(R.id.question_trignometry);
        trig.setOnClickListener(this);

        alg = findViewById(R.id.questions_algebra);
        alg.setOnClickListener(this);

        vec = findViewById(R.id.questions_vector);
        vec.setOnClickListener(this);

        comp =findViewById(R.id.questions_complex_numebr);
        comp.setOnClickListener(this);

        ans_trig =  findViewById(R.id.solutions_trignometry);
        ans_trig.setOnClickListener(this);

        ans_dif = findViewById(R.id.solutions_algebra);
        ans_dif.setOnClickListener(this);

        ans_vec =  findViewById(R.id.solutions_vector);
        ans_vec.setOnClickListener(this);

        ans_comp = findViewById(R.id.solutions_complex_numebr);
        ans_comp.setOnClickListener(this);

    }
    public void onClick (View v){
        String quiz = "";

        if(v == trig){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "trig";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
            myIntent.putExtra("subject",subject);
            startActivity(myIntent);

        }
        if(v == alg){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "alg";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
            myIntent.putExtra("subject",subject);
            startActivity(myIntent);
        }
        if(v == vec){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "vec";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
            myIntent.putExtra("subject",subject);
            startActivity(myIntent);
        }
        if(v == comp){
            Intent myIntent = new Intent(Texts_Math.this, Questions_multiple_choice.class);
            quiz = "comp";
            myIntent.putExtra("Quiz", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
            myIntent.putExtra("subject",subject);
            startActivity(myIntent);
        }


        if(v == ans_trig){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "trig";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_dif){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "dif";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_vec){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "vec";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }
        if(v == ans_comp){
            Intent myIntent = new Intent(Texts_Math.this, Solutions.class);
            quiz = "comp";
            myIntent.putExtra("Solution", quiz);
            startActivity(myIntent);
            myIntent.putExtra("username",username);
            startActivity(myIntent);
        }

    }
}