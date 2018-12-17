import java.util.List;

public class MyMain {

    public static void main(String[] args){
       EmailParser myparser = new EmailParser();

        List<Email> emails = myparser.read("./src/hillary_emails_subset.json");

        TextProcessor textProc = new TextProcessor();
        for (Email e : emails) {
            e.setProcessedBody(textProc.process(e.getBody()));
            e.setProcessedSubject(textProc.process(e.getSubject()));
        }
        CorpusRepresentation corpus = new CorpusRepresentation(emails, "body");

        TermExtractor.findTopTerms(corpus, 3);

        myparser.writeToFile("./src/hillary_emails_with_terms.json", corpus.getEmails());


/*int[][] mat = new int[2][3];
mat[0][0] = 0;
mat[0][1] = 1;
mat[0][2] = 2;
        mat[1][0] = 3;
        mat[1][1] = 4;
        mat[1][2] = 5;
for (int i=0;i<mat.length;i++){
    for(int j=0;j<mat[0].length;j++){
        System.out.print(mat[i][j]+" ");
    }
    System.out.println();
}



0 0 0 0
0 0 0 0
*/

       // myparser.writeToFile("./src/hillary_emails_terms.json",emails);

    }
}
