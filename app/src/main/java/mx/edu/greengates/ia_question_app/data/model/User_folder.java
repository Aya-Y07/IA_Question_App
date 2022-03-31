package mx.edu.greengates.ia_question_app.data.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class User_folder {

//    public final int UserID = 0;
    public final int UserID = 1;
    public final int UserName = 2;
    public final int Password = 3;
    public final int  Surname = 4;
    public final int FirstName = 5;
    public final int Email = 6;
    public final int Phone_number = 7;

    Context context;

    public String filename;
    private ArrayList<String[]> document_user;
    public User_folder(Context context, String filename){
        this.context = context;
        this.filename = filename;

    }

    public ArrayList<String[]> userFolder(){
        ArrayList<String[]> document_user = new ArrayList<>();
        try{
            InputStream inputStream = context.getAssets().open(this.filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader User_folder = new BufferedReader(inputStreamReader);
            String row;
            int row_num = 0;
            while((row = User_folder.readLine()) != null){
                String[] data = row .split(",");
                document_user.add(data);
            }
            User_folder.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error");
        }return document_user;
    }
    public void printDocument(){
        for (String[] row: document_user){
            for(int col = 0; col < row.length; col++){
                System.out.print(row[col] + " | ");
            }
            System.out.println();
        }
    }
    public static Users getUsersFromCSV(Context context){

        ArrayList<User> users = new ArrayList<User>();
        Users usersFromFile = new Users(users);

        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        try{
            String USER_FILE = "users.csv";
            is = assetManager.open(USER_FILE);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line ="";

            StringTokenizer st = null;
            int numLine = 0;
            while((line = reader.readLine())!=null){
                if(numLine > 0){
                    st = new StringTokenizer(line,",");

                    String UserID = st.nextToken();
                    String USERNAME = st.nextToken();
                    String PASSWORD = st.nextToken();
                    String FIRST_NAME = st.nextToken();
                    String SURNAME = st.nextToken();
                    String EMIL = st.nextToken();
                    String PHONE_NUMBER = st.nextToken();

                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return usersFromFile;
    }

    public List<User> createUsers() {

        List<User> userList = new ArrayList<>();
        int rowNum = 0;
        for (String[] row : document_user) {
            if (rowNum > 0) {
                String userId = row[UserID];
                String userName = row[UserName];
                String password = row[Password];
                String firstname = row[FirstName];
                String surname = row[Surname];
                String email = row[Email];
                String phoneNumber = row[Phone_number];

                User userObj = new User(userId,userName,password,firstname,surname,email,phoneNumber);
                userList.add(userObj);
            }
            rowNum++;
        }
        return userList;
    }


}

