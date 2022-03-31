package mx.edu.greengates.ia_question_app.data.model;


public class User {

    public String username;
    public String userId;
    public String password;
    public String emailaddress;
    public String surname;
    public String firstName;
    public String phone_Number;

    public User(String username, String userId, String password, String emailaddress, String surname, String firstName, String phone_Number){

        this.username = username;
        this.userId = userId;
        this.password = password;
        this.emailaddress = emailaddress;
        this.firstName = firstName;
        this.surname = surname;
        this.phone_Number = phone_Number;
    }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getUserId(){ return userId;}
    public void setUserId(String userId){this.userId = userId;}
    public String getEmailaddress(){return emailaddress;}
    public void setEmailaddress(String emailaddress){this.emailaddress = emailaddress;}
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public String getSurname(){return surname;}
    public void setSurname(String surname){this.surname = surname;}
    public String getPhone_Number(){return phone_Number;}
    public void setPhone_Number(String phone_Number){this.phone_Number = phone_Number;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}


    @Override
    public String toString(){
        return "Question{" + "Username ='" + username + '\'' + ", User ID = '" + userId + '\'' + ", Email address = '" + emailaddress + '\'' + ", First Name = '" + firstName + '\'' + ", Surname = '" + surname + '\'' + ", Phone number = '" + phone_Number + '\'' + '}';
    }
}

