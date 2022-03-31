package mx.edu.greengates.ia_question_app.data.model;

public class Mistaken_question {

    private String id;
    private String username;
    private String subject;

    public Mistaken_question(String id, String username, String subject){

        this.id = id;
        this.username = username;
        this.subject = subject;


    }
    public String getId(){return id;}
    public String getUsername(){ return username;}
    public String getSubject(){ return subject;}

    @Override
    public String toString(){
        return "Question{" + "id ='" + id + '\'' + ", User Name = '" + username  + '\'' + ", Subject = '" + subject  + '\'' + '}';
    }
}
