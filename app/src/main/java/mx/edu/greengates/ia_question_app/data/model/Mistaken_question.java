package mx.edu.greengates.ia_question_app.data.model;

public class Mistaken_question {

    private String id;
    private String username;
    private String subject;

    public Mistaken_question(String username, String id,String subject){

        this.id = id;
        this.username = username;
        this.subject = subject;


    }
    public String getId(){return id;}
    public String getUsername(){ return username;}
    public String getSubject(){ return subject;}

    @Override
    public String toString(){
        return "Question{" + ", User Name = '" + username  + '\'' + "id ='" + id + '\''  + ", Subject = '" + subject  + '\'' + '}';
    }
}
