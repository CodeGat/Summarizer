package Utilities;

import org.junit.Assert;
import org.junit.Test;

import static Utilities.Breaker.*;

public class BreakerTests {
    @Test public void sentenceBreakerTests(){
        Assert.assertArrayEquals("Test 1: Simple",
                new String[]{"Hello World.", "How are you."},
                sentence_breaker("Hello World. How are you."));

        Assert.assertArrayEquals("Test 2: no-space",
                new String[]{"Hello World.", "How are you."},
                sentence_breaker("Hello World.How are you."));

        Assert.assertArrayEquals("Test 3: no-space lowercase",
                new String[]{"Hello World.hows it going."},
                sentence_breaker("Hello World.hows it going."));

        Assert.assertArrayEquals("Test 4: Single Sentence",
                new String[]{"Hello World."},
                sentence_breaker("Hello World."));

        Assert.assertArrayEquals("Test 5: Other Punctuation",
                new String[]{"Hello World!", "How are you?", "Good!"},
                sentence_breaker("Hello World! How are you? Good!"));

        Assert.assertArrayEquals("Test 6: Other puncuation with no-space",
                new String[]{"Hello World?", "Hello!"},
                sentence_breaker("Hello World?Hello!"));

        Assert.assertArrayEquals("Test 7: Elipses",
                new String[]{"And then...", "something happened"},
                sentence_breaker("And then...something happened"));
    }

    @Test public void sentenceTerminatesHereConformingTests() {
        Assert.assertTrue("Test 1: n-index truthiness",
                sentenceTerminatesHere(9, "Something. Wow!"));

        Assert.assertTrue("Test 2: no-space truthiness",
                sentenceTerminatesHere(4, "lol.This is one too!"));
    }

    @Test public void sentenceTerminatesHereNonconformingTests(){
        Assert.assertFalse("Test 1: 0-index falsiness",
                sentenceTerminatesHere(0, ". not terminating"));

        Assert.assertFalse("Test 2: n-index no-space falsiness",
                sentenceTerminatesHere(20, "This might not work.to be honest"));

        Assert.assertFalse("Test 3: Mr. example falsiness",
                sentenceTerminatesHere(2, "Mr. Jones is the best! "));

        Assert.assertFalse("Test 4: US first period falsiness",
                sentenceTerminatesHere(1, "U.S. fleet"));

        Assert.assertFalse("Test 5: US second period falsiness",
                sentenceTerminatesHere(3, "U.S. fleet"));
    }

    @Test public void getPrevWordTests() {
        Assert.assertEquals("Test 1: Simple",
                "World",
                getPrevWord(11, "Hello World."));

        Assert.assertEquals("Test 2: single-word previous",
                "hello-world",
                getPrevWord(11, "hello-world. "));

        Assert.assertEquals("Test 3: Another one",
                "Babbage",
                getPrevWord(15, "Charles Babbage? Who is that? "));
    }

    @Test public void getNextWordTests(){
        Assert.assertEquals("Test 1: Simple",
                "Hello",
                getNextWord(0, ".Hello World!"));

        Assert.assertEquals("Test 2: No space after",
                "No",
                getNextWord(0, ".No"));

        Assert.assertEquals("Test 3: Sentence ending in another sentence",
                "No!",
                getNextWord(3, "Yes.No!"));
    }
}
