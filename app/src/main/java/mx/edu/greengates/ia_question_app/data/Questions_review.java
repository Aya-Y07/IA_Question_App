package mx.edu.greengates.ia_question_app.data;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.Question_folder;

public class Questions_review extends AppCompatActivity {


    private final TextView questionNumber = findViewById(R.id.questionNum);
    private final TextView question_mistaken_Text = findViewById(R.id.review_question);
    private final RadioGroup rgAnswers = findViewById(R.id.radioGroup);
    private final RadioButton radioAnswer1 = findViewById(R.id.ans_a);
    private final RadioButton radioAnswer2 = findViewById(R.id.ans_b);
    private final RadioButton radioAnswer3 = findViewById(R.id.ans_c);
    private final RadioButton radioAnswer4 = findViewById(R.id.ans_d);
    private int score = 0;
    private int quizNum = 1;
    private int Score;
    private String rightAnswer;
    public ArrayList<String> result;
    private ArrayList<StringTokenizer> review_questions;
    private ArrayList<StringTokenizer> questionList;
    private List<List<String>> quiz = null;
    private String username;
    private String subject;
    private int quizLength = 0;
    private List<String> finishedQuestions;
    private List<String> finishedAns;
    private String[][] QuestionLists;
    int listNum = 0;
    private String Question;
    private String Answer;


    ImageView imageView = findViewById(R.id.review_image);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_review);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        Intent intent1 = getIntent();
        subject = intent1.getStringExtra("Subject");

        Question_folder quizzes = new Question_folder();
        QuestionLists = quizzes.getQuestions();


        score = 0;


    }

    public void getQuestionsFromCSV(Context context) {


        AssetManager assetManager = context.getAssets();
        InputStream is;
        try {
            String MISTAKEN_QUESTION_FILE = "mistaken_question.csv";
            is = assetManager.open(MISTAKEN_QUESTION_FILE);
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line = "";

            StringTokenizer st;
            int numLine = 0;
            while ((line = reader.readLine()) != null) {
                if (numLine > 0) {
                    st = new StringTokenizer(line, ",");

                    String USERNAME = st.nextToken();
                    String QUESTION_ID = st.nextToken();
                    String SUBJECT = st.nextToken();

                    review_questions.add(st);

                    ListOfReview();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ListOfReview() {

        listNum = review_questions.size();
        int i = 0;
        while (i < listNum ){
            if (review_questions.get(0).equals(username) && review_questions.get(2).equals(subject)){
                questionList.add(review_questions.get(0));

                i++;
            }

        }

        int length = questionList.size();
        int count = 0;
        int count1 = 0;
        int length_of_questions = QuestionLists.length;
        while(count < length){
            while(count1 < length_of_questions){
                if(questionList.get(count).equals(QuestionLists[count1][1])){

                    quiz = Arrays.asList(
                            Arrays.asList(
                                    QuestionLists[count1][1],
                                    QuestionLists[count1][2],
                                    QuestionLists[count1][3],
                                    QuestionLists[count1][4],
                                    QuestionLists[count1][5],
                                    QuestionLists[count1][6])
                    );
                    break;
                }else{
                    count1 ++;

                }
            }
            count ++;
        }
        showQuestionsOnDisplay();
    }

    private void showQuestionsOnDisplay() {

        questionNumber.setText(getString(R.string.question_count, quizNum));

        quizLength = quiz.size();

        Random r = new Random();
        int randomNum = r.nextInt(quizLength);
        Question = quiz.get(randomNum).get(1);
        Answer = quiz.get(randomNum).get(2);

        if(Question.contains("img: ")){
            String quizText = Question;
            String imageID = quizText.substring(quizText.lastIndexOf("img: ")+5);

            question_mistaken_Text.setVisibility(View.GONE);

            imageView.setImageResource(Integer.parseInt(imageID));
            imageView.setVisibility(View.VISIBLE);
        }else {

            question_mistaken_Text.setVisibility(View.VISIBLE);
            question_mistaken_Text.setText(Question);
        }

        finishedQuestions.add(Question);
        rightAnswer = Answer;
        finishedAns.add(Question);

        int i = 2;

        ArrayList<List<String>> choice = new ArrayList<>();

        while (i < 6) {

            choice.add(Collections.singletonList(quiz.get(randomNum).get(i)));
            i += 1;
        }

        Collections.shuffle(choice);

        radioAnswer1.setText((CharSequence) choice.get(0));
        radioAnswer1.setChecked(false);
        radioAnswer1.setText((CharSequence) choice.get(1));
        radioAnswer2.setChecked(false);
        radioAnswer1.setText((CharSequence) choice.get(2));
        radioAnswer3.setChecked(false);
        radioAnswer1.setText((CharSequence) choice.get(3));
        radioAnswer4.setChecked(false);


        quiz.remove(randomNum);
    }

    private void checkAnswer() {


        Log.println(Log.DEBUG, "answer", "Correct answer =[" + rightAnswer + "]");
        int selected = rgAnswers.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selected);
        String userAns = radioButton.getText().toString().trim();

        String notification;

        if (userAns.equals(rightAnswer)) {
            score += 1;
            notification = "correct";
            result.add("○");

            int i = 0;
            while (i < listNum){
                if (review_questions.get(i).nextToken(String.valueOf(1)).equals(Question)){
                    review_questions.remove(review_questions.get(i));
                    i++;
                }
            }
            int count =0;
            try {
                FileWriter file = new FileWriter("questions.csv");
                PrintWriter pw = new PrintWriter(new BufferedWriter(file));
                while (listNum > count){
                    pw.println(review_questions.get(count));
                    count++;
                }
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            notification = "incorrect";
            result.add("×");
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(notification);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizNum == quizLength) {

                    Intent myIntent = new Intent(Questions_review.this, Result.class);
                    startActivity(myIntent);

                    Score = score;
                    myIntent.putExtra("score", Score);
                    startActivity(myIntent);

                    String page_name = "review";
                    myIntent.putExtra("Page Name", page_name);
                    startActivity(myIntent);



                } else {
                    quizNum++;
                    showQuestionsOnDisplay();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }

    public List<String> getFinishedReviewQuestions(){
        List<String> finishedquestions = this.finishedQuestions;
        return finishedQuestions;
    }
    public List<String> getFinishedReviewAns(){
        List<String> finishedans = this.finishedAns;
        return finishedAns;
    }
    public List<String> getReviewResult(){
        List<String> result = this.result;
        return result;
    }

}