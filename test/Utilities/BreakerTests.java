package Utilities;

import org.junit.Assert;
import org.junit.Test;


import static Utilities.Breaker.nextWordIsTerminating;

public class BreakerTests {
    @Test
    public void nextWordIsTerminatingTests(){
        Assert.assertTrue(nextWordIsTerminating(0, ". Hello world! "));
        Assert.assertTrue(nextWordIsTerminating(10, "Something. Wow!"));
        Assert.assertTrue(nextWordIsTerminating(4, "lol.This is one too!"));
    }
}
