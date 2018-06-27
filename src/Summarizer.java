import java.util.*;

public class Summarizer {
    public static void main(String[] args) {
        // TODO: crawl for web stories, instead just use system.in for now like the pleb you are.
        // TODO: 26/06/2018 Replace Hashmap with custom List<KVP> to allow bidirectional finding of keys, values
        // TODO: 26/06/2018 Write testing framework
        // TODO: 27/06/2018 prefers long sentences
        // TODO: 27/06/2018 sentence parsing breaks on '.*'
        Scanner in = new Scanner (System.in);
        ArrayList<String> raw_corpus = new ArrayList<>();
        while (in.hasNext()) raw_corpus.add(in.nextLine().toLowerCase());
        String corpus = raw_corpus
                .stream()
                .reduce("", (a, b) -> a+b);

        String[] sentences     = corpus.split("[.]");
        String[] words         = corpus.split("[ .,]");
        SignificantWords sig_words = new SignificantWords();

        for (String word : words) sig_words.add(word);
        sig_words.cleanup();
        sig_words.sortByValue();
        String[] msws = sig_words.getUpToNthSigWord(5);

        SignificantSentences sig_sentences = new SignificantSentences(sentences, msws);
        String[] ss = sig_sentences.getNMostSigSentences(5);
        System.out.println("Sigwords: "+Arrays.toString(msws) + "\nSigsents: " + Arrays.toString(ss));
    }
}
