package mx.edu.greengates.ia_question_app.data.model;

import java.util.List;

import mx.edu.greengates.ia_question_app.data.model.User;

public class Users {

    private final List<User> users;

    public Users(List<User> users){
        this.users = users;
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUser(int index){
        return users.get(index);
    }

    public void addUser(User user){
        users.add(user);
    }

}
