
import java.util.*;

/**
 * Represents the corpus of emails and performs different operations over the email contents.
 */
public class Corpus {

    private List<Email> emails;  //original emails
    private TypeOfContent typeOfContent; //what parts of email go into the corpus of texts (subject, body, both)
    private List<List<String>> texts; // the corpus of email texts after preprocessing (in the same order as emails)
    private List<String> words; //lexicon (all words seen in emails)
    private double [][] tfIdfScoreMatrix; //tf-idf scores

    public Corpus(List<Email> emails, TypeOfContent content){
        this.emails = emails;
        this.typeOfContent = content;
        initTexts();
        initWords();
        initTfIdfMatrix();
    }

    public Set<Term> getTerms(int textId){
        return emails.get(textId).getTerms();
    }

    public List<List<String>> getTexts(){ return this.texts; }

    public List<Email> getEmails(){ return this.emails; }

    /**
     *      Find words with N highest tf-idf scores
     *
     * @param topN how many top word you want
     *
     */
    public void findAndAddTopTerms(int topN){
        for(int j = 0; j< tfIdfScoreMatrix[0].length; j++){
            findAndAddTopTermsInText(topN, j);
        }

    }


    private void findAndAddTopTermsInText(int topN, int textId){

        double[] scores = getColumn(tfIdfScoreMatrix, textId);

        int numOfUniqueWordsInText = new HashSet<>(texts.get(textId)).size();

        if (topN > numOfUniqueWordsInText) {
            topN = numOfUniqueWordsInText;
        }

        class IndexComparator implements Comparator<Integer> {

            private final double[] array;

            public IndexComparator(double[] array) {
                this.array = array;
            }

            public Integer[] createIndexArray() {
                Integer[] indices = new Integer[array.length];
                for (int i = 0; i < array.length; i++){
                    indices[i] = i;
                }
                return indices;
            }

            @Override
            public int compare(Integer index1, Integer index2) {
                return Double.compare(array[index1], array[index2]);
            }
        }

        List<Term> topTerms = new ArrayList<>();
        IndexComparator comparator = new IndexComparator(scores);
        Integer[] wordIds = comparator.createIndexArray();
        Arrays.sort(wordIds, comparator);

        int lastId = words.size()-1;
        for (int i=lastId;i>lastId-topN;i--){
            topTerms.add(new Term(words.get(wordIds[i]), scores[wordIds[i]]));
        }

        addTerms(topTerms,textId);
    }

    private double[] getColumn(double[][] matrix, int colId){
        double[] column = new double[matrix.length];
        for (int i=0;i<matrix.length;i++){
            column[i]= matrix[i][colId];
        }
        return column;
    }

    private void initTexts(){

        List<List<String>> texts = new ArrayList<>();
        for (Email e : emails){
            switch (typeOfContent){
                case BODY:
                    texts.add(e.getProcessedBody());
                    break;

                case SUBJECT:
                    texts.add(e.getProcessedSubject());
                    break;

                case SUBJECT_AND_BODY:
                    texts.add(e.getProcessedSubjectAndBody());
                    break;
            }
        }
        this.texts = texts;
    }

    private void initTfIdfMatrix(){
        this.tfIdfScoreMatrix = new double[words.size()][texts.size()];
        for (int i=0;i<words.size();i++){
            double b = getIdf(texts,words.get(i));
            for (int j=0;j<texts.size();j++){
                double a = getTf(texts.get(j),words.get(i));
                this.tfIdfScoreMatrix[i][j] = a * b;
            }
        }

    }

    private double getTf(List<String> text, String word){

        if (text.isEmpty()){ return 0;}

        double count = 0;
        for (String w : text){
            if (w.equals(word)){
                count++;
            }
        }
        return count / text.size();

    }

    private double getIdf(List<List<String>> texts, String word){
        int count = 0;
        for (List<String> t : texts) {
            if (new HashSet<>(t).contains(word)) {
                count++;
            }
        }
        return Math.log(texts.size()/ (count + 1.0));
    }


    private void initWords(){

        Set<String> uniqueWords = new HashSet<>();

        for (List<String> t : getTexts()){
            if (!t.isEmpty()) {
                uniqueWords.addAll(t);
            }
        }
        this.words=new ArrayList<>(uniqueWords);
    }


    private void addTerms(List<Term> terms, int textId) {
        emails.get(textId).addAllTerms(terms);
    }


}
