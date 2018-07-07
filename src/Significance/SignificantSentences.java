package Significance;

public class SignificantSentences {
    private String[] sentences;
    private String[] words;
    private BiMap    sig_sentences;

    public SignificantSentences(String[] sentences, String[] words) {
        this.sentences = sentences;
        this.words = words;
        sig_sentences = new BiMap();
    }

    /**
     * adds all sentences to a BiMap and assigns a significance level to each.
     */
    public void findSigSentences() {
        for (String sentence : sentences){
            int sig = 0;

            for (int i = 0; i < words.length; i++) {
                if (sentence.contains(words[i])) {
                    sig += words.length - i;
                }
            }
            sig_sentences.add(sentence, sig);
        }
    }

    public void sortByMostSignificant(){
        sig_sentences.sortWithCount();
    }

    /**
     * Sorts the BiMap by the original ordering in the story or article - the tldr is more natural.
     */
    public void sortByOriginalOrdering(){
        BiMap sorted_sentences = new BiMap();

        for (String sentence : sentences){
            for (String sig_sentence : sig_sentences.getSigStrings()){
                if (sentence.equals(sig_sentence)){
                    sorted_sentences.add(sentence, sig_sentences.getCount(sig_sentence));
                }
            }
        }
        sig_sentences = sorted_sentences;
    }

    public void trim(int n){
        sig_sentences.trim(n);
    }

    public String[] getSentences(){ return sig_sentences.getSigStrings(); }
}
