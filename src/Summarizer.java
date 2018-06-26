import java.io.*;
import java.util.*;

public class Summarizer {
    static class Significance {
        private HashMap<String, Integer> word_count;
        private ArrayList<String>        common_words;

        Significance (){
            word_count   = new HashMap<>();
            common_words = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new FileReader("res/common_words.txt"));
                String line;
                while ((line=br.readLine()) != null) common_words.add(line);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        void cleanup() {
            word_count.remove("");
            word_count.entrySet().removeIf(kv -> common_words.contains(kv.getKey()));
        }

        //Utility Methods
        public String getSignificantWords(){ return word_count.keySet().toString(); }
        public String getSignificanceOfWords(){ return word_count.entrySet().toString(); }
        public int getSignificantWordLength(){ return word_count.entrySet().size(); }
    }

    public static void main(String[] args) {
        // TODO: crawl for web stories, instead just use system.in for now like the pleb you are.
        Scanner in = new Scanner (System.in);
        ArrayList<String> raw_corpus = new ArrayList<>();
        while (in.hasNext()) raw_corpus.add(in.nextLine());
        String corpus = raw_corpus.stream().reduce("", (a, b) -> a+b);


        String[] sentences     = corpus.split("[.]");
        String[] words         = corpus.split("[ .,]");
        Significance sig_words = new Significance();

        for (String word : words) sig_words.add(word.toLowerCase());
        sig_words.cleanup();
        String msw = sig_words.getSigWord();
        //ArrayList<String> msws = sig_words.getUpToAndIncludingNthSigWord(1);
        System.out.println(msw);
    }
}
