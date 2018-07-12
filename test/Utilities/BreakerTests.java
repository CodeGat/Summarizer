package Utilities;

import org.junit.Assert;
import org.junit.Test;


import static Utilities.Breaker.nextWordIsTerminating;
import static Utilities.Breaker.sentence_breaker;

public class BreakerTests {
    @Test public void sentenceBreakerTests(){
        Assert.assertArrayEquals("Test 1: Simple",
                new String[]{"Hello World. ", "How are you."},
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
                new String[]{"Hello World! ", "How are you? ", "Good!"},
                sentence_breaker("Hello World! How are you? Good!"));
        Assert.assertArrayEquals("Test 6: Other puncuation with no-space",
                new String[]{"Hello World?", "Hello!"},
                sentence_breaker("Hello World?Hello!"));
        Assert.assertArrayEquals("Test 7: Elipses",
                new String[]{"And then...", "something happened"},
                sentence_breaker("And then...something happened"));
    }

    @Test public void nextWordIsTerminatingTests(){
        Assert.assertTrue("Test 1: Basic 0-index truthiness",
                nextWordIsTerminating(0, ". Hello world! "));
        Assert.assertTrue("Test 2: n-index truthiness",
                nextWordIsTerminating(10, "Something. Wow!"));
        Assert.assertTrue("Test 3: no-space truthiness",
                nextWordIsTerminating(4, "lol.This is one too!"));

        Assert.assertFalse("Test 4: 0-index falsiness",
                nextWordIsTerminating(0, ". not terminating"));
        Assert.assertFalse("Test 5: n-index no-space falsiness",
                nextWordIsTerminating(20, "This might not work.to be honest"));
        Assert.assertFalse("Test 6: Mr. example falsiness",
                nextWordIsTerminating(2, "Mr. Jones is the best! "));
        Assert.assertFalse("Test 7: US first period falsiness",
                nextWordIsTerminating(1, "U.S. fleet"));
        Assert.assertFalse("Test 8: US second period falsiness",
                nextWordIsTerminating(3, "U.S. fleet"));
    }
}
