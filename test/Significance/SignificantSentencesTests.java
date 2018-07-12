package Significance;

import Significance.SignificantSentences;
import org.junit.Assert;
import org.junit.Test;

public class SignificantSentencesTests {
    private String[] sentences = new String[]{"hello world is babbages first program", //2+3+5+1=11
            "the first real application of programming was with babbage, and his hello world program", //1+4+5+2+3=15
            "the worlds first hello!", //3+1+2=6
            "babbage would have been programming to first write something about the world"}; //5+4+3=12
    private String[] words     = new String[]{"babbage", "programming", "world", "hello", "first"};

    @Test public void findMostSigSentencesTest(){
        SignificantSentences ss = new SignificantSentences(sentences, words);
        String[] expecteds = new String[]{"the first real application of programming was with babbage, and his hello world program",
                "babbage would have been programming to first write something about the world",
                "hello world is babbages first program",
                "the worlds first hello!"};

        ss.findSigSentences();
        ss.sortByMostSignificant();
        Assert.assertArrayEquals(expecteds, ss.getSentences());
    }

    @Test public void sortByOriginalOrderingTest(){
        SignificantSentences ss = new SignificantSentences(sentences, words);

        ss.findSigSentences();
        ss.sortByOriginalOrdering();
        Assert.assertArrayEquals(sentences, ss.getSentences());
    }

    @Test public void getNSentencesOrigOrderingTest(){
        SignificantSentences
                ss1 = new SignificantSentences(sentences, words),
                ss2 = new SignificantSentences(sentences, words),
                ss3 = new SignificantSentences(sentences, words);
        String[] expected1 = new String[]{"the first real application of programming was with babbage, and his hello world program"};
        String[] expected2 = new String[]{"the first real application of programming was with babbage, and his hello world program",
                "babbage would have been programming to first write something about the world"};
        String[] expected3 = new String[]{"hello world is babbages first program",
                "the first real application of programming was with babbage, and his hello world program",
                "babbage would have been programming to first write something about the world"};

        ss1.findSigSentences();
        ss1.sortByMostSignificant();
        ss1.trim(1);
        ss1.sortByOriginalOrdering();

        ss2.findSigSentences();
        ss2.sortByMostSignificant();
        ss2.trim(2);
        ss2.sortByOriginalOrdering();

        ss3.findSigSentences();
        ss3.sortByMostSignificant();
        ss3.trim(3);
        ss3.sortByOriginalOrdering();

        Assert.assertArrayEquals(expected1, ss1.getSentences());
        Assert.assertArrayEquals(expected2, ss2.getSentences());
        Assert.assertArrayEquals(expected3, ss3.getSentences());
    }

    @Test public void getNSentencesMostSignificantTest(){
        SignificantSentences
                ss1 = new SignificantSentences(sentences, words),
                ss2 = new SignificantSentences(sentences, words),
                ss3 = new SignificantSentences(sentences, words);
        String[] expected1 = new String[]{"the first real application of programming was with babbage, and his hello world program"};
        String[] expected2 = new String[]{"the first real application of programming was with babbage, and his hello world program",
                "babbage would have been programming to first write something about the world"};
        String[] expected3 = new String[]{"the first real application of programming was with babbage, and his hello world program",
                "babbage would have been programming to first write something about the world",
                "hello world is babbages first program"};

        ss1.findSigSentences();
        ss1.sortByMostSignificant();
        ss1.trim(1);

        ss2.findSigSentences();
        ss2.sortByMostSignificant();
        ss2.trim(2);

        ss3.findSigSentences();
        ss3.sortByMostSignificant();
        ss3.trim(3);

        Assert.assertArrayEquals("For trim to 1", expected1, ss1.getSentences());
        Assert.assertArrayEquals("For trim to 2", expected2, ss2.getSentences());
        Assert.assertArrayEquals("For trim to 3", expected3, ss3.getSentences());
    }
}
