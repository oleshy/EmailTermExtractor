import java.util.List;

public class TermExtractor {

    public static void main(String[] args){
      EmailParser emailParser = new EmailParser();

      List<Email> emails = emailParser.read("./src/resources/hillary_emails_subset.json");

      TextProcessor textProcessor = new TextProcessor();

      for (Email e : emails) {
          e.setProcessedBody(textProcessor.process(e.getBody()));
          e.setProcessedSubject(textProcessor.process(e.getSubject()));
      }
      Corpus corpus = new Corpus(emails, TypeOfContent.SUBJECT_AND_BODY,true);
      corpus.findAndAddTopTerms(3);


      emailParser.writeToFile("./src/resources/hillary_emails_with_terms.json", corpus.getEmails());

    }
}
