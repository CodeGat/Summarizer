public class SignificantSentences {
    String[] sentences;
    String[] words;

    public SignificantSentences(String[] sentences, String[] words) {
        this.sentences = sentences;
        this.words = words;
    }


    /**
     * @return the most significant sentence based on:
     *      - the amount of times the words appear
     *      - taking into account the significance of the words!!
     */
    public String getMostSigSentence() {
        String most_sig_sentence = "";
        int max_sig = 0;

        for (String sentence : sentences){
            int sentence_sig = 0;

            for (String word : words) if (sentence.contains(word)) sentence_sig++;
            if (sentence_sig >= max_sig) {
                max_sig = sentence_sig;
                most_sig_sentence = sentence;
            }
        }

        return most_sig_sentence;
    }
}
