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

public class WriteIntoUser_recordCSV {

    public final int USERNAME = 0;
    public final int ID = 1;
    public final int SCORE = 2;
    public final int ACCURACY_RATE = 3;
    public final int TIME = 4;
    public final int DATE = 5;
    public final int SUBJECT = 6;
    Context context;
    private String filename;
    private ArrayList<String[]> user_records;
    private ArrayList<String[]> RecordData;

    public WriteIntoUser_recordCSV(Context context, String filename) {
        this.context = context;
        this.user_records = this.readQuestionCSV(filename);
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


    public void writeRecordDataCSV(String fileName,String[] recordData) throws IOException {

        try {
            StringBuffer contentBuffer = new StringBuffer();
            int size = recordData.length;
            int count = 0;
            for(String data:recordData){
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
