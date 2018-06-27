import java.util.*;

class SignificantSentences {
    private String[] sentences;
    private String[] words;

    SignificantSentences(String[] sentences, String[] words) {
        this.sentences = sentences;
        this.words = words;
    }

    String[] getNMostSigSentences(int n) {
        String[] ordered_most_sig_sentences = new String[n];
        HashMap<String, Integer> most_sig_sentences = new HashMap<>();

        for (String sentence : sentences){
            int sig = 0;

            for (int i = 0; i < words.length; i++) {
                if (sentence.contains(words[i])) sig += words.length - i;
            }
            most_sig_sentences.put(sentence, sig);
        }

        Set<HashMap.Entry<String, Integer>> entries    = most_sig_sentences.entrySet();
        ArrayList<HashMap.Entry<String, Integer>> list = new ArrayList<>(entries);
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        for (int i = 0; i < n; i++) ordered_most_sig_sentences[i] = list.get(i).getKey();

        return ordered_most_sig_sentences;
    }
}
