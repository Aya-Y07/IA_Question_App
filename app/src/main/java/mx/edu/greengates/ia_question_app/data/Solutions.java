package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.Question_folder;

public class Solutions extends AppCompatActivity implements View.OnClickListener{

    private String[][] questions;

    private TextView questionNumber;
    private TextView questionText;
    private TextView solutionText;
    private TextView Answer;
    private ImageButton go_back;
    private ImageButton go_next;
    private Button go_home;


    private int count = 0;

    ImageView imageView_question;
    ImageView imageView_solution;

    ArrayList<ArrayList<String>> solutionArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        Question_folder quizzes = new Question_folder();
        questions = quizzes.getQuestions();

        questionNumber = findViewById(R.id.questionNum);
        questionText = findViewById(R.id.text_solution_question);
        solutionText = findViewById(R.id.text_solution);
        Answer = findViewById(R.id.text_solution_ans);
        imageView_question = findViewById(R.id.image_solution_question);
        imageView_solution = findViewById(R.id.image_solution);

        go_next = findViewById(R.id.btn_go_next_solution);
        go_next.setOnClickListener((View.OnClickListener) this);
        go_back = findViewById(R.id.btn_go_back_solution);
        go_back.setOnClickListener((View.OnClickListener)this);
        go_back.setVisibility(View.GONE);
        go_home = findViewById(R.id.btn_go_home);
        go_home.setOnClickListener((View.OnClickListener)this);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("Solution");



        for (int i = 0; i < questions.length; i++) {
            ArrayList<String> tmpArray = new ArrayList<>();
            String id = questions[i][0];
            if (id.contains(topic)) {
                tmpArray.add(questions[i][1]);
                tmpArray.add(questions[i][2]);
                tmpArray.add(questions[i][7]);

                solutionArray.add(tmpArray);
            }
        }
        showSolutionsOnDisplay();


    }

    /**
     * https://stackoverflow.com/questions/4427608/android-getting-resource-id-from-string
     * @param resourceName
     * @param c
     * @return
     */
    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }



    private void showSolutionsOnDisplay() {
        int quiz_num = count + 1;

        questionNumber.setText(getString(R.string.question_count,quiz_num));

        ArrayList<String> solution = solutionArray.get(count);


        Answer.setText(solution.get(1));

        if ((solution.get(2).contains("img"))){
            solutionText.setVisibility(View.GONE);
            imageView_solution.setVisibility(View.VISIBLE);
            String imageID = (solution.get(2)).substring(solution.get(2).lastIndexOf("img: ")+5);
            int resID = getId(imageID, R.drawable.class);
            imageView_solution.setImageResource(resID);

        }else{
            imageView_solution.setVisibility(View.GONE);
            solutionText.setVisibility(View.VISIBLE);
            solutionText.setText((solution.get(2)));
        }

        if ((solution.get(0).contains("img"))){
            questionText.setVisibility(View.GONE);
            imageView_question.setVisibility(View.VISIBLE);
            String imageID = (solution.get(0)).substring(solution.get(0).lastIndexOf("img: ")+5);
            int resID = getId(imageID, R.drawable.class);
            imageView_question.setImageResource(resID);

        }else{
            imageView_question.setVisibility(View.GONE);
            questionText.setVisibility(View.VISIBLE);
            questionText.setText((solution.get(0)));
        }

    }

    public void onClick (View v){
        if(v == go_next){
            count ++;
            if(count  == 3){
                go_next.setVisibility(View.GONE);
                go_back.setVisibility(View.VISIBLE);
            }if(count == 0){
                go_back.setVisibility(View.GONE);
                go_next.setVisibility(View.VISIBLE);
            }else{
                go_back.setVisibility(View.VISIBLE);
                go_next.setVisibility(View.VISIBLE);
            }
            showSolutionsOnDisplay();
        }
        if(v == go_back){
            count = count - 1;
            if(count == 3){

                go_next.setVisibility(View.GONE);
                go_back.setVisibility(View.VISIBLE);
            }if(count == 0){
                go_back.setVisibility(View.GONE);
                go_next.setVisibility(View.VISIBLE);
            }else{
                go_back.setVisibility(View.VISIBLE);
                go_next.setVisibility(View.VISIBLE);
            }
            showSolutionsOnDisplay();
        }
        if(v == go_home){
            Intent myIntent = new Intent(Solutions.this, Home.class);
            Solutions.this.startActivity(myIntent);
        }

    }

}