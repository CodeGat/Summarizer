import java.util.Arrays;

class SignificantSentences {
    private String[] sentences;
    private String[] words;

    SignificantSentences(String[] sentences, String[] words) {
        this.sentences = sentences;
        this.words = words;
    }

    String[] getNMostSigSentences(int n) {
        BiMap most_sig_sentences = new BiMap();

        for (String sentence : sentences){
            int sig = 0;

            for (int i = 0; i < words.length; i++) {
                if (sentence.contains(words[i])) sig += words.length - i;
            }
            most_sig_sentences.add(sentence, sig);
        }
        most_sig_sentences.sortWithCount();
        return Arrays.copyOf(most_sig_sentences.getSigStrings(), n);
    }
}
