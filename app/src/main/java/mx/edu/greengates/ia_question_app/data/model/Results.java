package mx.edu.greengates.ia_question_app.data.model;

import android.app.Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Results extends Application implements Serializable {
    private List<String> FinishedAns;
    private List<String>  FinishedQuestions;
    private List<String> Result;

    public Results() {
        FinishedAns = new ArrayList<>();
        FinishedQuestions = new ArrayList<>();
        Result = new ArrayList<>();
    }

    public List<String> getFinishedAns() {
        return FinishedAns;
    }

    public void setFinishedAns(List<String> finishedAns) {
        FinishedAns = finishedAns;
    }

    public List<String> getFinishedQuestions() {
        return FinishedQuestions;
    }

    public void setFinishedQuestions(List<String> finishedQuestions) {
        FinishedQuestions = finishedQuestions;
    }

    public List<String> getResult() {
        return Result;
    }

    public void setResult(List<String> result) {
        Result = result;
    }
}
