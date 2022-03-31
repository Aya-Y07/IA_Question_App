package mx.edu.greengates.ia_question_app.data.model;

import java.util.List;

import mx.edu.greengates.ia_question_app.data.model.User_record;

public class Users_record {
    private final List<User_record> mistakes ;

    public Users_record(List<User_record> mistakes){
        this.mistakes = mistakes;
    }

    public List<User_record> getMistakes(){
        return mistakes;
    }

    public User_record getMistakes(int index){
        return mistakes.get(index);
    }

    public void addMistakes(User_record mistake){
        mistakes.add(mistake);
    }

}
