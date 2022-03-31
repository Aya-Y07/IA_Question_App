package mx.edu.greengates.ia_question_app.data.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {

    private String id;
    private String question;
    private String answer;
    private String A0;
    private String A1;
    private String A2;
    private String A3;
    private String solution;

    public Question(String id, String question, String answer, String A0, String A1, String A2, String A3,String Solution){

        this.id = id;
        this.question = question;
        this.answer = answer;
        this.A0 = A0;
        this.A1 = A1;
        this.A2 = A2;
        this.A3 = A3;
        this.solution = solution;
    }
    public String getId(){return id;}
    public String getQuestion(){ return question;}
    public String getAnswer(){return answer;}
    public String getSolution(){return solution;}
    public String[]getOptions(){
        String[] options = new String[4];
        options[0] = A0;
        options[0] = A1;
        options[0] = A2;
        options[0] = A3;
        return shuffleOptions(options);

    }

    private String[] shuffleOptions(String[] options){
        List<String> strList = Arrays.asList(options);
        Collections.shuffle(strList);
        strList.toArray(options);
        return options;
    }
    @Override
    public String toString(){
        return "Question{" + "id ='" + id + '\'' + ", question = '" + question + '\'' + ", answer = '" + answer + '\'' + ", solution = '" + solution + '\''  + '}';
    }
}
