import java.util.ArrayList;
import java.util.List;

public class Email {
    private String Id;
    private String ExtractedSubject;
    private String ExtractedBodyText;
    private String RawText;
    private transient List<String> processedSubject;
    private transient List<String> processedBody;
    private List<Term> terms;


    public Email(){
        this.Id = "";
        this.ExtractedSubject = "";
        this.ExtractedBodyText = "";
        this.RawText = "";
        this.terms = new ArrayList<Term>();
    }

    public String getBody() {   return ExtractedBodyText;  }

    public String getSubject() { return ExtractedSubject; }

    public String getId(){ return Id; }


    public void setProcessedSubject(List<String> processedSubject) {
        this.processedSubject = processedSubject;
    }

    public void setProcessedBody(List<String> processedBody) {
        this.processedBody = processedBody;
    }

    public List<String> getProcessedSubject() {
        return this.processedSubject;
    }

    public List<String> getProcessedBody() {
        return this.processedBody;
    }

    public List<String> getProcessedSubjectAndBody() {
        List<String> subjectAndBody = new ArrayList<>();
        subjectAndBody.addAll(this.processedSubject);
        subjectAndBody.addAll(this.processedBody);
        return subjectAndBody;

    }

    public void setTerms(List<Term> terms){ this.terms = terms;  }

    public void addTerm(Term t){ this.terms.add(t); }



 /*   public void fromJson(JSONObject o){
        this.id = ((Long) o.get("Id")).intValue();
        this.subject = (String) o.get("ExtractedSubject");
                this.body = (String) o.get("ExtractedBodyText");
                this.rawtext = (String) o.get("RawText");
    }

    public JSONObject toJson(){
        JSONObject email = new JSONObject();
        email.put("Id",id);
        email.put("Terms", terms);
        email.put("ExtractedSubject",subject);
        email.put("ExtractedBodyText",body);
        email.put("RawText",rawtext);
        return email;
    }
    */

}