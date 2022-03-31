package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Timer;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.WriteIntoUserCSV;


public class Result extends AppCompatActivity {

    Questions_multiple_choice results = new Questions_multiple_choice();
    List<String>  FinishedQuestions = results.getFinishedQuestions();
    List<String> FinishedAns = results.getFinishedAns();
    List<String> Result = results.getResult();


    Questions_review werk_points_results = new Questions_review();
    List<String>  FinishedReviewQuestions = werk_points_results .getFinishedReviewQuestions();
    List<String> FinishedReviewAns = werk_points_results .getFinishedReviewAns();
    List<String> ReviewResult = werk_points_results .getResult();


    private String page_name;
    private String username;
    private String subject;
    private String time;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        page_name = intent.getStringExtra("Page Name");
        Intent intent1 = getIntent();
        username = intent.getStringExtra("Username");
        Intent intent2 = getIntent();
        subject = intent.getStringExtra("Subject");
        Intent intent3 = getIntent();
        time = intent.getStringExtra("Time");
        Intent intent4 = getIntent();
        date = intent.getStringExtra("Date");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button go_Home = findViewById(R.id.btn_go_home);
        go_Home.setOnClickListener((View.OnClickListener)this);

        TextView accuracy_rate = findViewById(R.id.accuracy_rate);
        TextView totalScore = findViewById(R.id.score);

        TableLayout tableLayout = findViewById(R.id.tablelayout);
        tableLayout.removeAllViews();


        int score = getIntent().getIntExtra("score", 0);

        int Accuracy_rate;
        Accuracy_rate = (score/4)*100;

        accuracy_rate.setText(Accuracy_rate);
        totalScore.setText(score);

        String[] userData = {username, String.valueOf(score), String.valueOf(accuracy_rate),time,date,subject};
        WriteIntoUserCSV writer = new WriteIntoUserCSV(this,"Users_record.csv");


        try{
            writer.writeUserDataCSV("Users_record.csv",userData);
        }catch (IOException e) {

            e.printStackTrace();

        }

        if(page_name == "Question"){
            for(int i=0; i<FinishedQuestions.size();i++){
                String question  = FinishedQuestions.get(i);
                String answer = FinishedAns.get(i);
                String result = Result.get(i);

                TableRow tableRow = new TableRow(this);

                final TextView columnQuestionView = new TextView(this);
                columnQuestionView.setText(String.format("%7s", question));
                final TextView columnAnswerView = new TextView(this);
                columnAnswerView.setText(String.format("%7s", answer));
                final TextView columnResultView = new TextView(this);
                columnResultView.setText(String.format("%7s", result));
                tableRow.addView(columnQuestionView);
                tableRow.addView(columnAnswerView);
                tableRow.addView(columnResultView);

                tableLayout.addView(tableRow);
            }

        }else{
            for(int i=0; i<FinishedQuestions.size();i++){
                String question  = FinishedReviewQuestions.get(i);
                String answer = FinishedReviewAns.get(i);
                String result = ReviewResult.get(i);

                TableRow tableRow = new TableRow(this);

                final TextView columnQuestionView = new TextView(this);
                columnQuestionView.setText(String.format("%7s", question));
                final TextView columnAnswerView = new TextView(this);
                columnAnswerView.setText(String.format("%7s", answer));
                final TextView columnResultView = new TextView(this);
                columnResultView.setText(String.format("%7s", result));
                tableRow.addView(columnQuestionView);
                tableRow.addView(columnAnswerView);
                tableRow.addView(columnResultView);

                tableLayout.addView(tableRow);
            }
        }




    }

}