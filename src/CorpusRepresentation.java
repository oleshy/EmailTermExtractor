import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CorpusRepresentation {

    /**
     * Represents the corpus of emails.
     */

    private double [][] matrix; //tf-idf scores
    private List<List<String>> texts;
    private List<String> words;
    private List<Email> emails;
    String TYPE_OF_CONTENT;

    public CorpusRepresentation(List<Email> emails, String content){
        this.emails = emails;
        this.TYPE_OF_CONTENT = content;
        initTexts();
        initWords();
        initMatrix();
    }

    private void initTexts(){

        List<List<String>> texts = new ArrayList<>();
        for (Email e : emails){
            switch (TYPE_OF_CONTENT){
                case "body":
                    texts.add(e.getProcessedBody());
                    break;

                case "subject":
                    texts.add(e.getProcessedSubject());
                    break;

                case "both" :
                    texts.add(e.getProcessedSubjectAndBody());
                    break;
            }
        }
        this.texts = texts;
    }

    private void initMatrix(){
        this.matrix = new double[words.size()][texts.size()];
        int i = 0;
        for (String w : words){
            for (int j=0;j<texts.size();j++){
                this.matrix[i][j] = getTf(texts.get(j),w) * getIdf(texts,w);
            }
            i++;
        }

    }

    private double getTf(List<String> text, String word){

        if (text.isEmpty()){ return 0;}

        int count = 0;
        for (String w : text){
            if (w.equals(word)){
                count++;
            }
        }
        return count / text.size();
    }

    private double getIdf(List<List<String>> texts, String word){
        int count = 0;
        for (List<String> t : texts){
            if (new HashSet<String>(t).contains(word)){
                count++;
            }
        }
        return Math.log(texts.size()/ (count + 1.0));
    }

    private void initWords(){

            Set<String> uniqueWords = new HashSet<>();

            for (List<String> t : getTexts()){
                uniqueWords.addAll(t);
            }
            this.words=new ArrayList<>(uniqueWords);
    }

    public void addTerms(List<String> words){
        //TODO
    }

    public double[][] getMatrix(){
        return matrix;
    }

    public List<String> getWords() {
        return words;
    }

    public List<List<String>> getTexts(){ return this.texts; }

    public List<Email> getEmails(){ return this.emails; }

}
