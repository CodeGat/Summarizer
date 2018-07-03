import Significance.SignificantSentences;
import org.junit.Assert;
import org.junit.Test;

public class SignificantSentencesTests {
    @Test
    public void getNMostSigSentencesSimpleTest(){
        String[] sents = {"one of the most used phrases in programming is hello world",
                "this was coopted by charles babbages analytical engine in which he first sent hello world",
                "hence the first programming use of hello world was with babbages analytical engine"}; // (4+3=7), (4+2+1=7), (4+3+2+1=10)
        String[] words = {"world", "programming", "analytical", "engine"};
        String[] expecteds = {"hence the first programming use of hello world was with babbages analytical engine",
                "one of the most used phrases in programming is hello world"};

        SignificantSentences sigsents = new SignificantSentences(sents, words);
        Assert.assertArrayEquals(expecteds, sigsents.getNMostSigSentences(2));
    }
}
