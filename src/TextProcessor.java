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

    //TODO add lemmatization

   /*public List<String> processCoreNLP(String text){

       Properties properties = new Properties();
       properties.put("annotators", "tokenize,ssplit,pos,lemma");
       StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
       Annotation annotation = pipeline.process(text);

       List<CoreMap> tokens = annotation.get(CoreAnnotations.SentencesAnnotation.class);

       List<String> output = new ArrayList<>();

       Set<String> stopWords = getStopWords();

                for (CoreMap tok : tokens){

                    if (!stopWords.contains(tok.toString())){
                        output.add(tok.toString().toLowerCase());
                    }

           }

       return output;
   }
*/

    /**
     * Downcases and filters out stop words, punctuation and digits.
     * @return
     */
   public List<String> process(String text){

       List<String> words = new ArrayList<String>();
       Set<String> stopWords = getStopWords();
       Pattern digits = Pattern.compile("[0-9][0-9]*");
       String newtext = text.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();

       for (String w : newtext.split("\\s+")){

           Matcher digMatcher = digits.matcher(w);

           if(!(stopWords.contains(w) || digMatcher.matches())){
               words.add(w.toLowerCase());
           }
       }
       return words;
   }

   private Set<String> getStopWords(){
       Set<String> stopWords = new HashSet<>();
       try {
           BufferedReader reader = new BufferedReader(new FileReader("./resources/stopwords"));
           String aline = reader.readLine();
           while(aline != null){
               for (String s : aline.split(",")){
                   stopWords.add(s);
               }
               aline = reader.readLine();
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException ioe){
           ioe.printStackTrace();
       }
       return stopWords;
   }
}
