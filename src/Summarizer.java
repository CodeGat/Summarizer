import java.util.*;

public class Summarizer {
    public static void main(String[] args) {
        // TODO: crawl for web stories, instead just use system.in for now like the pleb you are.
        // TODO: 26/06/2018 Replace Hashmap with custom List<KVP> to allow bidirectional finding of keys, values
        // TODO: 26/06/2018 Write testing framework
        Scanner in = new Scanner (System.in);
        ArrayList<String> raw_corpus = new ArrayList<>();
        while (in.hasNext()) raw_corpus.add(in.nextLine());
        String corpus = raw_corpus
                .stream()
                .reduce("", (a, b) -> a+b);

        String[] sentences     = corpus.split("[.]");
        String[] words         = corpus.split("[ .,]");
        Significance sig_words = new Significance();

        for (String word : words) sig_words.add(word.toLowerCase());
        sig_words.cleanup();
        sig_words.sortByValue();
        ArrayList<String> msws = sig_words.getUpToNthSigWord(5);

        // TODO: 26/06/2018 find sentences with significant words in them
    }
}
