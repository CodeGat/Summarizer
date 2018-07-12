package Utilities;

import java.util.ArrayList;

public class Breaker {
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
            boolean does_terminate = sentenceTerminatesHere(i, corpus);
            if ((ch == '.' || ch == '?' || ch == '!') && does_terminate){
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
     * @param corpus the string corpus that is used for finding the next word.
     * @return true if it is the start of a new sentence.
     */
    //conditions: [.?!] [A-Z0-9] | [.?!][A-Z0-9]
    // TODO: 12/07/2018 Terminating word is based on previous one sometimes! Mr. X depends on Mr.!
    static boolean sentenceTerminatesHere(int stop_index, String corpus){
//        try {
//            if (corpus.charAt(stop_index + 1) == ' ') {
//                stop_index++;
//            }
//        } catch (StringIndexOutOfBoundsException sioobx) {
//            System.err.println("Caught end of sentence");
//        }

        for (int i = stop_index; i < corpus.length(); i++) {
            String ch = Character.toString(corpus.charAt(i));
            if (ch.matches("[A-Z]") || i == corpus.length() - 1) return true;
//            else if (ch.equals(" ")) return false;
        }
        return false;
    }

    public static String[] word_breaker(String corpus){
        return null;
    }
}
