package mx.edu.greengates.ia_question_app.data;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.Question_folder;
import mx.edu.greengates.ia_question_app.data.model.Results;

public class Questions_multiple_choice extends AppCompatActivity {


    public List<String> finishedQuestions;
    public List<String> finishedAns;
    public List<String> result;
    private String username;
    private String subject;

    ImageView imageView;

    private String [][] questions;

    private TextView question_num, question;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 4;
    private String question_ID;



    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    Results results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_multiple_choice);

        results = (Results) getApplicationContext();

        Intent myIntent = getIntent();
        username = myIntent.getStringExtra("username");
        subject = myIntent.getStringExtra("subject");
        String topic = myIntent.getStringExtra("Quiz");

        Question_folder quizzes = new Question_folder();
        questions = quizzes.getQuestions();

        question_num = findViewById(R.id.question_num);
        question = findViewById(R.id.question);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);
        imageView = findViewById(R.id.imageView);

        finishedQuestions = new ArrayList<>();
        finishedAns = new ArrayList<>();
        result = new ArrayList<>();

        //creating a quiz list for specific topic

        for (int i = 0; i < questions.length; i++) {
            ArrayList<String> tmpArray = new ArrayList<>();
            String id = questions[i][0];
            if (id.contains(topic)) {
                tmpArray.add(questions[i][0]);
                tmpArray.add(questions[i][1]);
                tmpArray.add(questions[i][2]);
                tmpArray.add(questions[i][3]);
                tmpArray.add(questions[i][4]);
                tmpArray.add(questions[i][5]);
                tmpArray.add(questions[i][6]);

                quizArray.add(tmpArray);
            }
        }
        displayQuestion();
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

    public void displayQuestion () {

        //setting question number
        question_num.setText(getString(R.string.count_label, quizCount));

        // get random num
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // use random number to get a question list from quizArray randomly
        ArrayList<String> quiz = quizArray.get(randomNum);

        //display question on a screen
        if ((quiz.get(1).contains("img"))){
            question.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            String imageID = (quiz.get(1)).substring(quiz.get(1).lastIndexOf("img: ")+5);
            int resID = getId(imageID, R.drawable.class);
            imageView.setImageResource(resID);

        }else{
            imageView.setVisibility(View.GONE);
            question.setVisibility(View.VISIBLE);
            question.setText((quiz.get(1)));
        }
        finishedQuestions.add(quiz.get(1));

        //get right answer
        rightAnswer = quiz.get(2);
        finishedAns.add(rightAnswer);


        results.setFinishedQuestions(finishedQuestions);
        results.setFinishedAns(finishedAns);

        question_ID = quiz.get(0);

        //get choices (4 choices)
        ArrayList<String> choice = new ArrayList<>();
        int count = 3;
        while (count < 7){
            choice.add(quiz.get(count));
            count ++;
        }


        //shaffle choices
        Collections.shuffle(choice);


        // display choices on screen with 4 buttoms
        answerBtn1.setText(choice.get(0));
        answerBtn2.setText(choice.get(1));
        answerBtn3.setText(choice.get(2));
        answerBtn4.setText(choice.get(3));

        // remove a question list that have finished from quizArray
        quizArray.remove(randomNum);

    }

    public void checkAnswer (View view){


        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if (btnText.equals(rightAnswer)) {
            alertTitle = "Correct";
            result.add("○");
            results.setResult(result);
            rightAnswerCount++;
        } else {
            alertTitle = "Incorrect";

            try {
                File file = new File(this.getFilesDir(), "mistaken_question.csv");
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);

                StringBuffer sb = new StringBuffer();
                sb.append(username);
                sb.append(",");
                sb.append(question_ID);
                sb.append(",");
                sb.append(subject);
                String credential = sb.toString();
                Log.d("Questions_multiple_choice", credential);

                bw.write(credential);
                bw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            result.add("×");
            results.setResult(result);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {

                    //go to the result page
                    Intent intent = new Intent(Questions_multiple_choice.this, Result.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    Date now = new Date();
                    intent.putExtra("Date", now);
                    intent.putExtra("Username", username);
                    String page_name = "Question";
                    intent.putExtra("Page Name", page_name);
                    intent.putExtra("Subject", subject);
                    startActivity(intent);

                } else {
                    quizCount++;
                    displayQuestion();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }


}
