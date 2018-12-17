public class Term {
    String term;

    public Term(String term){
        this.term = term;
    }

    public String getTerm(){
        return this.term;
    }

    @Override
    public String toString() {
        return "\""+term+"\"";
    }
}
