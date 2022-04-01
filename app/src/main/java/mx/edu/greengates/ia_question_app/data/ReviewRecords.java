package mx.edu.greengates.ia_question_app.data;

public class ReviewRecords {
    private String review_no;
    private String review_question;
    private String review_answer;
    private String review_result;

    public void UserReviewRecord(
            String review_no,
            String review_question,
            String review_answer,
            String review_result) {
        review_no = review_no;
        review_question = review_question;
        review_answer = review_answer;
        review_result = review_result;
    }

    public String getReview_no() {
        return review_no;
    }
    public String getReview_question() {
        return review_question;
    }
    public String getReview_answer() {
        return review_answer;
    }
    public String getReview_result() { return review_result; }
}
