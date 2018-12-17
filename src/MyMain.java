import java.util.List;

public class MyMain {

    public static void main(String[] args){
      EmailParser myparser = new EmailParser();

        List<Email> emails = myparser.read("./src/resources/hillary_emails_subset.json");

        TextProcessor textProc = new TextProcessor();
        for (Email e : emails) {
            e.setProcessedBody(textProc.process(e.getBody()));
            e.setProcessedSubject(textProc.process(e.getSubject()));
        }
        Corpus corpus = new Corpus(emails, "body");
        corpus.findAndAddTopTerms(2);

        myparser.writeToFile("./src/resources/hillary_emails_with_terms.json", corpus.getEmails());

    }
}
