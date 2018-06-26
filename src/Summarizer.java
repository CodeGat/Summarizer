import java.util.*;

public class Summarizer {
    static class Significance {
        HashMap<String, Integer> word_count;

        Significance (){
            word_count = new HashMap<>();
        }

        /**
         * @param word the word to be added.
         */
        void add(String word){
            if (word_count.containsKey(word)) word_count.put(word, word_count.get(word)+1);
            else word_count.put(word, 1);
        }

        String getSigWord() {
            Integer highest_value = Collections.max(word_count.values());

            for (HashMap.Entry entry : word_count.entrySet()){
                if (entry.getValue().equals(highest_value)) return (String) entry.getKey();
            }
            return null;
        }

        // TODO: 26/06/2018 Add nth sig-word getter
        ArrayList<String> getUpToAndIncludingNthSigWord(int n) {
            return null;
        }
    }

    public static void main(String[] args) {
        // TODO: crawl for web stories, instead just use system.in for now like the pleb you are.
        Scanner in = new Scanner (System.in);
        ArrayList<String> raw_corpus = new ArrayList<>();
        while (in.hasNext()) raw_corpus.add(in.nextLine());
        String corpus = raw_corpus.stream().reduce(null, (a, b) -> a+b);


        String[] sentences     = corpus.split("[.]");
        String[] words         = corpus.split("[ .,]");
        Significance sig_words = new Significance();

        for (String word : words) sig_words.add(word);
        String msw = sig_words.getSigWord();
        ArrayList<String> msws = sig_words.getUpToAndIncludingNthSigWord(1);
    }
}
