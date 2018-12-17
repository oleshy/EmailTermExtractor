import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TextProcessorTest {

    @Test
    public void process() {

        TextProcessor testProc = new TextProcessor();
        List<String> result = testProc.process("U.S. Department of State Case No. F-2015-04841 about us and them!");
        List<String> expected = Arrays.asList("u.s","department","state", "case", "f-2015-04841", "us");
        Assert.assertEquals(expected, result);


    }


}