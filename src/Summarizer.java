import Significance.SignificantSentences;
import Significance.SignificantWords;
import WebCrawler.Crawler;

import java.util.*;

public class Summarizer {
    private static int N_SIG_WORDS     = 5;
    private static int N_SIG_SENTENCES = 5;

    public static void main(String[] args) {
        String corpus = "";
        switch (args.length) {
            case 3:
                N_SIG_WORDS = Integer.valueOf(args[0]);
                N_SIG_SENTENCES = Integer.valueOf(args[1]);
                corpus = Crawler.crawl(args[2]);
                break;
            case 1:
                corpus = Crawler.crawl(args[0]);
                break;
            case 0:
                Scanner in = new Scanner(System.in);
                System.out.print("> ");
                corpus = Crawler.crawl(in.next());
                break;
            default:
                System.out.println("Usage: Summarizer.java [[n_sig_words n_sig_sentences] url]\nwhere:\n\tn_sig_words" +
                        " - number of significant words to use to determine a significant sentence\n\tn_sig_sentences" +
                        " - number of significant sentences to return\n\turl - the url of the paper or story");
                System.exit(0);
        }

        String[] sentences     = corpus.split("\\.");
        String[] words         = corpus.toLowerCase().split("[ .,]");
        SignificantWords sig_words = new SignificantWords();

        for (String word : words) sig_words.add(word);
        sig_words.cleanup();
        sig_words.sort();
        String[] msws = sig_words.getUpToNthSigWord(N_SIG_WORDS);

        SignificantSentences sig_sentences = new SignificantSentences(sentences, msws);
        sig_sentences.findSigSentences();
        sig_sentences.sortByMostSignificant();
        sig_sentences.trim(N_SIG_SENTENCES);
        sig_sentences.sortByOriginalOrdering();
        String[] oss = sig_sentences.getSentences();

        StringBuilder oss_res = new StringBuilder();
        for (String s : oss) oss_res.append(s).append(". \n");
        System.out.print("\n"+oss_res.toString()+"\nSignificant Words: ");
        for (int i = 0; i < msws.length; i++) System.out.print(i+1+": "+msws[i]+", ");
    }
}
