package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.WriteIntoUserCSV;

public class Result_Review extends AppCompatActivity {


    Questions_review werk_points_results = new Questions_review();
    List<String>  FinishedReviewQuestions = werk_points_results .getFinishedReviewQuestions();
    List<String> FinishedReviewAns = werk_points_results .getFinishedReviewAns();
    List<String> ReviewResult = werk_points_results.getReviewResult();


    private String page_name;
    private String username;
    private String subject;
    private String time;
    private String date;
    private int count = 0;
    private List<List<String>> list = null;


    private TableLayout mTableLayout;


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
        setContentView(R.layout.activity_result_review);

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



        mTableLayout = findViewById(R.id.tablePlayers);
        mTableLayout.setStretchAllColumns(true);
        startLoadData();

        int i = FinishedReviewQuestions.size();

        while ( count > i ){
            list = Collections.singletonList(Arrays.asList(
                    String.valueOf((count + 1)),
                    FinishedReviewQuestions.get(count),
                    FinishedReviewAns.get(count),
                    ReviewResult.get(count))

            );
            count ++;
        }
    }


    public void startLoadData() {

        new LoadDataTask().execute(0);
    }
    public void loadData() {
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 0, smallTextSize =0, mediumTextSize = 0;
        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);


        int rows = list.size();
        TextView textSpacer = null;
        mTableLayout.removeAllViews();

        for(int i = -1; i < rows; i ++) {
            ReviewRecords row = null;
            if (i > -1)
                row = (ReviewRecords) list.get(i);
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");
            }

            final TextView tv = new TextView(this);
            tv.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.LEFT);
            tv.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv.setText("No");
                tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv.setText(String.valueOf(row.getReview_no()));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            final TextView tv2 = new TextView(this);
            tv2.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setGravity(Gravity.LEFT);
            tv2.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv2.setText("Question");
                tv2.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv2.setText(row.getReview_question());
            }

            final TextView tv3 = new TextView(this);
            tv3.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setGravity(Gravity.LEFT);
            tv3.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv3.setText("Answer");
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3.setText(row.getReview_answer());
            }

            final TextView tv4 = new TextView(this);
            tv3.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv3.setGravity(Gravity.LEFT);
            tv3.setPadding(5, 15, 0, 15);
            if (i == -1) {
                tv3.setText("Result");
                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv3.setText(row.getReview_result());
            }

            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new
                    TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);
            tr.addView(tv);
            tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv4);
            mTableLayout.addView(tr, trParams);

            if (i > -1) {
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new
                        TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 4;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(1);
                trSep.addView(tvSep);
                mTableLayout.addView(trSep, trParamsSep);
            }
        }
    }
    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            loadData();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
        }
    }

}