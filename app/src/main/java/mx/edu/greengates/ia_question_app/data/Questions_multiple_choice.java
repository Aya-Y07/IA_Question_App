package mx.edu.greengates.ia_question_app.data;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.Question_folder;
import mx.edu.greengates.ia_question_app.data.model.WriteIntoMistaken_QuestionCSV;

public class Questions_multiple_choice extends AppCompatActivity {

    private TextView questionNumber;
    private TextView questionText;
    private TextView Timer;
    private RadioGroup rgAnswers;
    private RadioButton radioAnswer1;
    private RadioButton radioAnswer2;
    private RadioButton radioAnswer3;
    private RadioButton radioAnswer4;
    private int score;
    private int quizNum;
    private int Score;
    private String Answer;
    private Timer timer;
    private long period;
    private int count;
    private SimpleDateFormat Time;
    private final int QUIZ_COUNT = 4;
    private final SimpleDateFormat dataFormat = new SimpleDateFormat("mm:ss.S", Locale.US);
    private final Handler handler = new Handler(Looper.getMainLooper());
    public ArrayList<String> finishedQuestions;
    public ArrayList<String> finishedAns;
    public ArrayList<String> result;
    private List<StringTokenizer> questions;
    private List<List<String>> quiz = null;
    private String currQuestion;
    private String username;
    private String subject;

    ImageView imageView;
    int imageNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_multiple_choice);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        Intent intent1 = getIntent();
        subject = intent.getStringExtra("subject");

        Question_folder quizzes = new Question_folder();
        questions = quizzes.getQuestionList();


        questionNumber = findViewById(R.id.questionNum);
        questionText = findViewById(R.id.Question_text);
        rgAnswers = findViewById(R.id.radioGroup);
        radioAnswer1 = findViewById(R.id.ans_a);
        radioAnswer2 = findViewById(R.id.ans_b);
        radioAnswer3 = findViewById(R.id.ans_c);
        radioAnswer4 = findViewById(R.id.ans_d);
        imageView = findViewById(R.id.imageView);
        imageNum = 1;

        quizNum = 1;
        score = 0;

    }


    public void ListOfQuiz() {


        Intent intent = getIntent();
        String topic = intent.getStringExtra("Quiz");

        int coulumnNum = questions.size();

        while (coulumnNum > 0) {

            String id = questions.get(coulumnNum).nextToken(String.valueOf(0));
            if (id.contains(topic)) {

                quiz = Collections.singletonList(
                        Arrays.asList(
                                questions.get(coulumnNum).nextToken(String.valueOf(0)),
                                questions.get(coulumnNum).nextToken(String.valueOf(1)),
                                questions.get(coulumnNum).nextToken(String.valueOf(2)),
                                questions.get(coulumnNum).nextToken(String.valueOf(3)),
                                questions.get(coulumnNum).nextToken(String.valueOf(4)),
                                questions.get(coulumnNum).nextToken(String.valueOf(5)),
                                questions.get(coulumnNum).nextToken(String.valueOf(6)))
                );
                coulumnNum -= 1;

            } else {
                coulumnNum -= 1;
            }
        }
        showQuestionsOnDisplay();
    }


    private void showQuestionsOnDisplay() {

        questionNumber.setText(getString(R.string.question_count,quizNum));

        Random r = new Random();
        int randomNum = r.nextInt(quiz.size());
        String Question = quiz.get(randomNum).get(1);
        Answer = quiz.get(randomNum).get(2);

        if(Question.contains("img: ")){
            String quizText = Question;
            String imageID = quizText.substring(quizText.lastIndexOf("img: ")+5);

            questionText.setVisibility(View.GONE);

            imageView.setImageResource(Integer.parseInt(imageID));
            imageView.setVisibility(View.VISIBLE);
        }else{

            questionText.setVisibility(View.VISIBLE);
            questionText.setText(Question);
        }

        finishedQuestions.add(Question);

        finishedAns.add(Answer);
        currQuestion = Question;


        int i = 2;
        ArrayList<List<String>> choice = new ArrayList<>();

        while (i < 6){

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


    protected void measureTime(Bundle savedInstanceState) {

        long delay = 0;
        period = 100;

        Timer = findViewById(R.id.txtTime);
        Timer.setText(dataFormat.format(0));

        if (null != timer) {
            timer.cancel();
            timer = null;
        }

        timer = new Timer();

        count = 0;
        Timer.setText(dataFormat.format(0));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        count++;
                        Timer.setText(dataFormat.format(count * period));
                    }
                });
            }
        }, delay, period);
        checkAnswer();
    }

    private void checkAnswer(){


        int selected = rgAnswers.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selected);
        String userAns = radioButton.getText().toString().trim();

        String notification;

        if (userAns.equals(Answer)){
            score += 1;
            notification = "correct";
            result.add("○");
        }else{
            notification = "incorrect";
            result.add("×");

            String[] questionData = {username,currQuestion,subject};

            WriteIntoMistaken_QuestionCSV writer = new WriteIntoMistaken_QuestionCSV(this, "mistaken_question.csv");
            try {
                writer.writeQuestionDataCSV("mistaken_question.csv", questionData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(notification);
        builder.setMessage("Answer : " + Answer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizNum == QUIZ_COUNT) {

                    Intent myIntent = new Intent(Questions_multiple_choice.this, Result.class);
                    Questions_multiple_choice.this.startActivity(myIntent);

                    Score = score;
                    myIntent.putExtra("score", Score);  
                    startActivity(myIntent);



                    if (null != timer) {
                        timer.cancel();
                        timer = null;
                        Timer.setText(Questions_multiple_choice.this.dataFormat.format(0));
                        Time = Questions_multiple_choice.this.dataFormat;

                    }
                    myIntent.putExtra("Time", Time);
                    startActivity(myIntent);

                    Date now = new Date();
                    myIntent.putExtra("Date", now);
                    startActivity(myIntent);

                    myIntent.putExtra("Username", username);
                    startActivity(myIntent);

                    String page_name = "Question";
                    myIntent.putExtra("Page Name", page_name);
                    startActivity(myIntent);

                    myIntent.putExtra("Subject", subject);
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

    public List<String> getFinishedQuestions(){
        return finishedQuestions;
    }
    public List<String> getFinishedAns(){
        return finishedAns;
    }
    public List<String> getResult(){
        return result;
    }




}

