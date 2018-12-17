import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Document;
import edu.stanford.nlp.ling.tokensregex.TokenSequenceMatcher;
import edu.stanford.nlp.ling.tokensregex.TokenSequencePattern;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TextProcessor is a class for initial text processing such as tokenization, stemming and stop-word filtering
 *
 */
public class TextProcessor {

    private static Set<String> stopWords;
    static {
         stopWords = getStopWords();
    }

    /**
     * Downcases and filters out stop words, punctuation and digits.
     * @return the processed text in a form of list of tokens
     */
   public List<String> process(String text){

       List<String> words = new ArrayList<>();
       Pattern digits = Pattern.compile("[0-9][0-9]*");

       for (String w : text.split("\\s+|--")){

           String newW = w.replaceFirst("^[^a-zA-Z0-9]+", "").replaceAll("[^a-zA-Z0-9]+$","").toLowerCase();
           Matcher digMatcher = digits.matcher(newW);

           if(!newW.equals("") && !(stopWords.contains(newW) || digMatcher.matches())){
               words.add(newW);
           }
       }
       return words;
   }

   private static Set<String> getStopWords(){
       Set<String> stopWords = new HashSet<>();
       try (BufferedReader reader = new BufferedReader(new FileReader("./src/resources/stopwords"))){
           String aline = reader.readLine().trim();
           while(aline != null){
               stopWords.add(aline.trim());
               aline = reader.readLine();
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException ioe) {
           ioe.printStackTrace();
       }
       return stopWords;
   }
}
