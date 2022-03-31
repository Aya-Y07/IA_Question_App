package mx.edu.greengates.ia_question_app.data.model;

public class User_record {

    private String username;
    private String score;
    private String accuracy_rate;
    private String time_taken;
    private String date;
    private String subject;


    public User_record( String username, String score, String accuracy_rate, String time_taken, String date,String subject){


        this.username = username;
        this.score = score;
        this.accuracy_rate = accuracy_rate;
        this.time_taken = time_taken;
        this.date = date;
        this.subject = subject;


    }

    public String getUsername(){ return username;}
    public String getScore(){ return score;}
    public String getAccuracy_rate(){ return accuracy_rate;}
    public String getTime_taken(){ return time_taken;}
    public String getDate(){ return date;}
    public String getSubject(){ return subject;}

    @Override
    public String toString(){
        return "Question{" + ", User Name = '" + username  + '\'' +  ", Score = '" + score  + '\'' + ", Accuracy rate = '" + accuracy_rate  + '\'' + ", Time Taken = '" + time_taken  + '\'' + ", Date = '" + date  + '\'' + ", Subject = '" + subject  + '\'' + '}';
    }
}
