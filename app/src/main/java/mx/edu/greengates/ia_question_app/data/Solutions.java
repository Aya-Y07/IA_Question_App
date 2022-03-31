package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.Question_folder;

public class Solutions extends AppCompatActivity {

    private List<StringTokenizer> questions;
    private List<List<String>> solution = null;

    private TextView questionNumber;
    private TextView questionText;
    private TextView solutionText;
    private TextView Answer;
    private ImageButton go_back;
    private ImageButton go_next;
    private Button go_home;


    private int count = 0;
    private int solutionCount;

    ImageView imageView_question;
    ImageView imageView_solution;
    int imageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        Question_folder quizzes = new Question_folder();
        questions = quizzes.getQuestionList();

        questionNumber = findViewById(R.id.questionNum);
        questionText = findViewById(R.id.text_solution_question);
        solutionText = findViewById(R.id.text_solution);
        Answer = findViewById(R.id.text_solution_ans);
        imageView_question = findViewById(R.id.image_solution_question);

        imageView_solution = findViewById(R.id.image_solution);

        go_next = findViewById(R.id.btn_go_next_solution);
        go_next.setOnClickListener((View.OnClickListener) this);
        go_next.setEnabled(true);
        go_back = findViewById(R.id.btn_go_back);
        go_back.setOnClickListener((View.OnClickListener)this);
        go_back.setEnabled(false);
        go_home = findViewById(R.id.btn_go_home);
        go_home.setOnClickListener((View.OnClickListener)this);


    }

    public void ListOfSolutions() {


        Intent intent = getIntent();
        String topic = intent.getStringExtra("Solution");

        int coulmnNum = questions.size();

        while (coulmnNum > 0) {

            String id = questions.get(coulmnNum).nextToken(String.valueOf(0));
            if (id.contains(topic)) {

                solution = Collections.singletonList(
                        Arrays.asList(
                                questions.get(coulmnNum).nextToken(String.valueOf(1)),
                                questions.get(coulmnNum).nextToken(String.valueOf(2)),
                                questions.get(coulmnNum).nextToken(String.valueOf(7)))
                );
                coulmnNum -= 1;

            } else {
                coulmnNum -= 1;
            }
        } showSolutionsOnDisplay();
    }


    private void showSolutionsOnDisplay() {

        questionNumber.setText(getString(R.string.question_count,count+1));
        solutionCount = solution.size();

        if(solution.get(1).contains("img: ")){
            String quizText = solution.get(1).get(count);
            String imageID = quizText.substring(quizText.lastIndexOf("img: ")+5);

            questionText.setVisibility(View.GONE);

            imageView_question.setImageResource(Integer.parseInt(imageID));
            imageView_question.setVisibility(View.VISIBLE);
        }else {
            questionText.setText(solution.get(0).get(count));
        }

        if(solution.get(1).contains("img: ")){
            String solutionText = solution.get(1).get(count);
            String imageID = solutionText.substring(solutionText.lastIndexOf("img: ")+5);

            questionText.setVisibility(View.GONE);

            imageView_solution.setImageResource(Integer.parseInt(imageID));
            imageView_solution.setVisibility(View.VISIBLE);
        }else {
            solutionText.setText(solution.get(2).get(count));
        }


        Answer.setText(solution.get(1).get(count));


        if (count > 0){
            go_back.setEnabled(true);
        }else{
            go_back.setEnabled(false);
        }


    }

    public void onClick (View v){
        if(v == go_next){
            count ++;
            if(count == solutionCount){

                go_next.setEnabled(false);
            }else{
                go_next.setEnabled(true);
            }
        }
        if(v == go_next){
            count -= 1;
        }
        if(v == go_home){
            Intent myIntent = new Intent(Solutions.this, Home.class);
            Solutions.this.startActivity(myIntent);
        }

    }

}