import java.util.*;

public class Summarizer {
    private final static int N_SIG_WORDS     = 5;
    private final static int N_SIG_SENTENCES = 5;

    public static void main(String[] args) {
        // TODO: crawl for web stories, instead just use system.in for now like the pleb you are.
        // TODO: 27/06/2018 prefers long sentences
        // TODO: 27/06/2018 sentence parsing - doesn't like regex(*U.S. *)
        Scanner in = new Scanner (System.in);
        ArrayList<String> raw_corpus = new ArrayList<>();
        while (in.hasNext()) raw_corpus.add(in.nextLine().toLowerCase());
        String corpus = raw_corpus
                .stream()
                .reduce("", (a, b) -> a+b);

        String[] sentences     = corpus.split("\\.");
        String[] words         = corpus.split("[ .,]");
        SignificantWords sig_words = new SignificantWords();

        for (String word : words) sig_words.add(word);
        sig_words.cleanup();
        sig_words.sort();
        String[] msws = sig_words.getUpToNthSigWord(N_SIG_WORDS);

        SignificantSentences sig_sentences = new SignificantSentences(sentences, msws);
        String[] ss = sig_sentences.getNMostSigSentences(N_SIG_SENTENCES);
        System.out.println("Sigwords: "+Arrays.toString(msws) + "\nSigsents: ");
        StringBuilder res = new StringBuilder();
        for (String s : ss) res.append(s).append("\n");
        System.out.println(res.toString());
    }
}
