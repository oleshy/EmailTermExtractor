import java.util.*;

public class TermExtractor {
    /**
     * Converts text data into vector representation and performs statistical analysis to extract the keywords from texts.
     */




    private void runSVD(double[][] matrix){

    }

    /**
     * Find all words with scores above a certain threshold for each text
     * @param corpus the collection of emails
     * @param threshold the threshold
     * @return
     */
    public static List<List<String>> findTopTerms(CorpusRepresentation corpus, double threshold){


        double[][] matrix = corpus.getMatrix();

        List<List<String>> topWordsInTexts = new ArrayList<>();

//TODO
        return null;
    }


    /**
    Find words with N highest tf-idf scores for one text
     */
    private static List<String> findTopWordsInText(List<String> words, double[] scores, int topN){

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

        List<String> topWords = new ArrayList<>();
        IndexComparator comparator = new IndexComparator(scores);
        Integer[] wordIds = comparator.createIndexArray();
        Arrays.sort(wordIds);

        int lastId = words.size()-1;
        for (int i=lastId;i>lastId-topN;i--){
            topWords.add(words.get(i));
        }

        return topWords;
    }

    public static List<List<String>> findTopWords(CorpusRepresentation corpus, int topN){
        //for ()
        return null;

    }






}
