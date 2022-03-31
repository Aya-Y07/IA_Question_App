package mx.edu.greengates.ia_question_app.data.model;

import mx.edu.greengates.ia_question_app.data.model.Question;

public class QuestionResult extends Question {

    boolean result;

    public QuestionResult(String id, String question, String answer, String A0, String A1, String A2, String A3, String Solution) {
        super(id, question, answer, A0, A1, A2, A3, Solution);
    }

    int selecteAnswer;

}
