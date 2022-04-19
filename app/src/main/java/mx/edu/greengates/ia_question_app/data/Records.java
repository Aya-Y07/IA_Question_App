package mx.edu.greengates.ia_question_app.data;


public class Records {
    private String no;
    private String question;
    private String answer;
    private String result;

    public Records(int count, Object s, Object s1, Object s2) {
    }

    public void UserRecord(
            String no,
            String question,
            String answer,
            String result) {
        no = no;
        question = question;
        answer = answer;
        result = result;
    }

    public String getNo() {
        return no;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
    public String getResult() { return result; }
}
