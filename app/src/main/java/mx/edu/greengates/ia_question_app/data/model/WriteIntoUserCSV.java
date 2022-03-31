package mx.edu.greengates.ia_question_app.data.model;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WriteIntoUserCSV {


    public final int USERNAME = 0;
    public final int PASSWORD = 1;
    public final int EMAIL = 2;
    public final int SURNAME = 3;
    public final int FIRST_NAME = 4;
    public final int PHONE_NUMBER = 5;
    Context context;
    private String filename;
    private ArrayList<String[]> questions;
    private ArrayList<String[]> userData;


    public WriteIntoUserCSV(Context context, String filename) {
        this.context = context;
        this.questions = this.readUserCSV(filename);
    }

    public ArrayList<String[]> readUserDataCSV(String fileName){
        ArrayList<String[]> document = new ArrayList<>();

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader csvReader = new BufferedReader(inputStreamReader);

            String row;
            int rowNum = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                document.add(data);
            }
            csvReader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("Error" + ioe.getMessage());
        }
        return document;
    }

    public void writeUserDataCSV(String fileName,String[] userData) throws IOException {

        try {
            StringBuffer contentBuffer = new StringBuffer();
            int size = userData.length;
            int count = 0;
            for(String data:userData){
                contentBuffer.append(data);
                if(count < size - 1){
                    contentBuffer.append(",");
                    count++;
                }
            }
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(contentBuffer.toString().getBytes());
            bufferedOutputStream.close();

        } catch (IOException e){
            throw new IOException("Unable to access Application Data.");
        }

    }

    public ArrayList<String[]> readUserCSV(String filename){
        ArrayList<String[]> document = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader csvReader = new BufferedReader(inputStreamReader);
            String row;
            int rowNum = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                document.add(data);
            }
            csvReader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.out.println("Error" + ioe.getMessage());
        }
        return document;
    }
}


