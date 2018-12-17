import java.util.Objects;

public class Term {
    String word;
    double score; //tf-idf score

    public Term(String word){
        this.word = word;
    }

    public Term(String word, double score){
        this.word = word;
        this.score = score;
    }

    public String getWord(){
        return this.word;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "\""+ word +" : "+score+" "+"\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term1 = (Term) o;
        return Double.compare(term1.score, score) == 0 &&
                Objects.equals(word, term1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, score);
    }
}
