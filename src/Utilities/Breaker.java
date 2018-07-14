package Utilities;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Breaker {
    private static String[] nonterminators = {"Mr", "Mrs", "Ms", "Dr"};
    private static String[] special_words  = {"US", "RADAR"};

    public Breaker(){}

    /**
     * Splits a corpus into their seperate sentences, mindful of non-terminating periods like 'Mr.'.
     * @param corpus the corpus that will be split
     * @return the sentences in a String[].
     */
    public static String[] sentence_breaker(String corpus){
        ArrayList<String> sentences = new ArrayList<>();

        int sentence_start = 0;
        for (int i = 0; i < corpus.length(); i++){
            char ch = corpus.charAt(i);
            if ((ch == '.' || ch == '?' || ch == '!') && sentenceTerminatesHere(i, corpus)){
                sentences.add(corpus.substring(sentence_start, i+1));
                sentence_start = i+1;
            } else if (corpus.charAt(sentence_start) == ' ') {
                sentence_start = i+1;
            }
        }

        return sentences.toArray(new String[0]);
    }

    /**
     * Checks if the next word is terminating (i.e. it is the start of a new sentence, as opposed to a non-terminating
     * period like in 'Mr.' or 'U.S.').
     * @param stop_index the index of the full stop.
     * @param corpus the text of which we are finding the termination state.
     * @return true if it is the start of a new sentence.
     */
    static boolean sentenceTerminatesHere(int stop_index, String corpus){
        String prev_word = getPrevWord(stop_index, corpus);
        if (Stream.of(nonterminators).anyMatch(x -> x.equals(prev_word))) return false;

        // a quick check to see if there is a space after the period. If so, advance over it.
        if (stop_index + 1 < corpus.length() && corpus.charAt(stop_index + 1) == ' ') {
            stop_index += 2;
        }

        String next_word = getNextWord(stop_index, corpus);
        if (Stream.of(special_words).anyMatch(x -> x.equals(prev_word + next_word))) return false;
        return next_word.matches("[A-Z].*|\\.|");
    }

    static String getNextWord(int stop_index, String corpus) {
        String   cut_corpus = corpus.substring(stop_index);
        String[] words      = cut_corpus.split("[.?! ]");

        if (words.length > 0) return words[0].equals("") ? words[1] : words[0];
        else return "";
    }

    static String getPrevWord(int stop_index, String corpus) {
        String   cut_corpus = corpus.substring(0, stop_index);
        String[] words      = cut_corpus.split("[.?! ]");

        return words[words.length - 1];
    }
}
