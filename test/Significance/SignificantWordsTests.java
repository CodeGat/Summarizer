package Significance;

import org.junit.Assert;
import org.junit.Test;

public class SignificantWordsTests {
    @Test public void addNewUncommonSigWordTests(){
        SignificantWords sigwords = new SignificantWords();
        sigwords.add("foobar");
        Assert.assertEquals("<foobar, 1>", sigwords.getSignificanceOfWords());
    }

    @Test public void addAnotherUncommonSigWordTest(){
        SignificantWords sigwords = new SignificantWords();
        sigwords.add("foobar");
        sigwords.add("foobar");
        Assert.assertEquals("<foobar, 2>", sigwords.getSignificanceOfWords());
    }

    @Test public void cleanupSigStringsTest(){
        SignificantWords sigwords = new SignificantWords();
        sigwords.add("");
        sigwords.add("foobar");
        sigwords.add("you");
        sigwords.add("the");
        sigwords.add("java");
        sigwords.cleanup();
        Assert.assertEquals("<foobar, 1><java, 1>", sigwords.getSignificanceOfWords());
    }

    @Test public void sortSigStringsTest(){
        SignificantWords sigwords = new SignificantWords();
        sigwords.add("foobar");
        sigwords.add("foobar");
        sigwords.add("foobar");
        sigwords.add("java");
        sigwords.add("madagascar");
        sigwords.add("madagascar");
        sigwords.sort();
        Assert.assertEquals("<foobar, 3><madagascar, 2><java, 1>", sigwords.getSignificanceOfWords());
    }

    @Test public void getUpToNthSigWordTest(){
        SignificantWords sigwords = new SignificantWords();
        sigwords.add("foobar");
        sigwords.add("foobar");
        sigwords.add("foobar");
        sigwords.add("java");
        sigwords.add("madagascar");
        sigwords.add("madagascar");
        sigwords.add("australia");
        sigwords.add("foobar");
        sigwords.add("antarctica");
        sigwords.sort();
        Assert.assertArrayEquals(sigwords.getUpToNthSigWord(3), new String[]{"foobar", "madagascar", "java"});
    }
}
