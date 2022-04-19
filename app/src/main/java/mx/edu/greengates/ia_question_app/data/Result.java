package mx.edu.greengates.ia_question_app.data;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import mx.edu.greengates.ia_question_app.R;
import mx.edu.greengates.ia_question_app.data.model.Results;

public class Result extends AppCompatActivity {

    private Questions_multiple_choice quizzes;
    private List<String> FinishedAns;
    private List<String>  FinishedQuestions;
    private List<String> Result;
    private Results info;


    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        info = (Results) getApplicationContext();
        mProgressBar = new ProgressDialog(this);
        mTableLayout = (TableLayout) findViewById(R.id.tablePlayers);
        mTableLayout.setStretchAllColumns(true);

        startLoadData();
    }



    public void startLoadData() {
        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching Results..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new LoadDataTask().execute(0);
    }

    public Records[] getResults_info() {
        // info = new Results();
        FinishedQuestions = info.getFinishedQuestions();
        FinishedAns = info.getFinishedAns();
        Result = info.getResult();

        //create a list that have been solved, (FinishedQuestions, FinishedAns and Result are come from Questions_multiple_choice)
        Records[] data = new Records[4];
        while (count < FinishedQuestions.size()){
            data[count] = new Records(count,FinishedQuestions.get(count),FinishedAns.get(count),Result.get(count));
            count ++;
        }
        return data;
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
        mx.edu.greengates.ia_question_app.data.Result results = new Result();
        Records[] data = results.getResults_info();
        int rows = data.length;
        //add title
        getSupportActionBar().setTitle("Results");
        TextView textSpacer = null;
        mTableLayout.removeAllViews();
        //for title
        for(int i = -1; i < rows; i ++) {
            Records row = null;
            if (i > -1)
                row = data[i];
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");
            }
// first row (no)
//save the data (no) into TextView
            final TextView row1 = new TextView(this);
            row1.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row1.setGravity(Gravity.LEFT);
            row1.setPadding(5, 15, 0, 15);
            if (i == -1) {
                row1.setText("No");
                row1.setBackgroundColor(Color.parseColor("#f0f0f0"));
                row1.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                //displaying (no) on the screen
                row1.setText(String.valueOf(row.getNo()));
                row1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
// second row (Question)
            final TextView row2 = new TextView(this);
            row2.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row2.setGravity(Gravity.LEFT);
            row2.setPadding(5, 15, 0, 15);
            if (i == -1) {
                row2.setText("Question");
                row2.setBackgroundColor(Color.parseColor("#f0f0f0"));
                row2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                row2.setText(row.getQuestion());
            }
// third row (Answer)
            final TextView row3 = new TextView(this);
            row3.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row3.setGravity(Gravity.LEFT);
            row3.setPadding(5, 15, 0, 15);
            if (i == -1) {
                row3.setText("Answer");
                row3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                row3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                row3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                row3.setText(row.getAnswer());
            }
// forth row (result)
            final TextView row4 = new TextView(this);
            row4.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            row4.setGravity(Gravity.LEFT);
            row4.setPadding(5, 15, 0, 15);
            if (i == -1) {
                row4.setText("Result");
                row4.setBackgroundColor(Color.parseColor("#f0f0f0"));
                row4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                row4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                row4.setText(row.getResult());
            }
// add rows to the table
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new
                    TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0,0,0,0);
//adding all data for a column to tr
            tr.setLayoutParams(trParams);
            tr.addView(row1);
            tr.addView(row2);
            tr.addView(row3);
            tr.addView(row4);



            //setting tr to mTableLayout
            //repeat from the firth row until all rows are sved into mTableLayout
            mTableLayout.addView(tr, trParams);
            // add ruled line
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
            mProgressBar.hide();
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