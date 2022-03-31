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

public class WriteIntoMistaken_QuestionCSV {

    public final int USERNAME = 0;
    public final int ID = 1;
    public final int SUBJECT = 2;
    Context context;
    private String filename;
    private ArrayList<String[]> questions;
    private ArrayList<String[]> questionData;



    public WriteIntoMistaken_QuestionCSV(Context context, String filename) {
        this.context = context;
        this.questions = this.readQuestionCSV(filename);
    }


    public ArrayList<String[]> readCSV(String fileName){
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


    public void writeQuestionDataCSV(String fileName,String[] questionData) throws IOException {

        try {
            StringBuffer contentBuffer = new StringBuffer();
            int size = questionData.length;
            int count = 0;
            for(String data:questionData){
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


    public ArrayList<String[]> readQuestionCSV(String filename){
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
