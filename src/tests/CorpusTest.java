import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CorpusTest {

    private List<Email> emails = new ArrayList<>();
    private Set<Term> terms0= new HashSet<>();
    private Set<Term> terms1 = new HashSet<>();
    private Set<Term> terms4 = new HashSet<>();

    @Before
    public void prepareEmails(){

        Email e0 = new Email();
        e0.setProcessedBody(Arrays.asList("teacher", "practice", "hard", "work", "student"));
        e0.setProcessedSubject(Arrays.asList("saying", "one"));

        terms0 =  new HashSet<Term>(Arrays.asList(new Term("practice", 0.2505525936990736), new Term("hard",0.2505525936990736)));

        Email e1 = new Email();
        e1.setProcessedBody(Arrays.asList("trust", "choice", "correct", "wrong", "trust", "choice", "student", "student", "student"));
        e1.setProcessedSubject(Arrays.asList("saying", "two"));

        terms1 =  new HashSet<Term>(Arrays.asList(new Term("trust", 0.2783917707767484), new Term("choice",0.2783917707767484)));

        Email e2 = new Email();
        e2.setProcessedBody(Arrays.asList("teacher", "show", "path", "walk", "master", "student"));
        e2.setProcessedSubject(Arrays.asList("saying", "three"));

        Email e3 = new Email();
        e3.setProcessedBody(Arrays.asList("words", "translate", "power", "easy", "work", "teacher", "master"));
        e3.setProcessedSubject(Arrays.asList("saying", "four"));

        Email e4 = new Email();
        e4.setProcessedBody(Arrays.asList("lego", "warrior", "figure", "sale", "buy", "quality"));
        e4.setProcessedSubject(Arrays.asList("limited", "opportunity", "sale"));

        terms4 =  new HashSet<Term>(Arrays.asList(new Term("lego", 0.20879382808256133), new Term("warrior",0.20879382808256133)));

        Email e5 = new Email();
        e5.setProcessedBody(Arrays.asList("now", "buy", "deluxe", "figure", "batman", "sale"));
        e5.setProcessedSubject(Arrays.asList("buy", "now"));


        Email e6 = new Email();
        e6.setProcessedBody(Arrays.asList("now", "get", "quality", "percent", "extra", "sale", "dresses"));
        e6.setProcessedSubject(Arrays.asList("sale"));

        emails.add(e0);
        emails.add(e1);
        emails.add(e2);
        emails.add(e3);
        emails.add(e4);
        emails.add(e5);
        emails.add(e6);

    }

    @Test
    public void findAndAddTopTerms() {
        Corpus testCorpus = new Corpus(emails, "body");
        testCorpus.findAndAddTopTerms(2);
        Set<Term> result0 =  testCorpus.getTerms(0);
        Set<Term> result1 = testCorpus.getTerms(1);
        Set<Term> result4 = testCorpus.getTerms(4);

        Assert.assertEquals(terms0, result0);
        Assert.assertEquals(terms1, result1);
        Assert.assertEquals(terms4, result4);

        System.out.println("Unit indAndAddTopTerms() finished without error.");
    }


}