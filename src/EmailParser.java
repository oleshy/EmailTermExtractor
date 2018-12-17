import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EmailParser converts texts from JSON objects to Java objects and back.
 */


public class EmailParser {

    private final static Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public List<Email> read(String filename){

        List<Email> emails = new ArrayList<>();

        try{
           emails = GSON.fromJson(new FileReader(filename), new TypeToken<List<Email>>(){}.getType());
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return emails;
    }

    public void writeToFile(String filename, List<Email> emails){

        try (Writer writer = new FileWriter(filename)) {
            GSON.toJson(emails, writer);

        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }



 }