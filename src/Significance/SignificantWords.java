package Significance;

import java.io.*;
import java.util.*;

public class SignificantWords {
    private BiMap word_count;
    private ArrayList<String> common_words;

    public SignificantWords(){
        word_count   = new BiMap();
        common_words = new ArrayList<>();
        try {
            InputStream in = getClass().getResourceAsStream("/common_words.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
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
    public void add(String word){
        if (word_count.containsSigstring(word)) {
            word_count.increment(word);
        } else {
            word_count.add(word, 1);
        }
    }

    /**
     * @param n the inclusive upper-bound index of the signficant words to return.
     * @return an array of the first n words.
     */
    public String[] getUpToNthSigWord(int n) {
        String[] keys = word_count.getSigStrings();
        return Arrays.copyOf(keys, n);
    }

    /**
     * removes the empty entry as well as any common words or symbols.
     */
    public void cleanup() {
        String[] sigstrs = word_count.getSigStrings();
        word_count.removeUsingSigstring("");
        for (String sigstr : sigstrs) {
            if (common_words.contains(sigstr)) {
                word_count.removeUsingSigstring(sigstr);
            }
        }
    }

    /**
     * calls down to a BiMap sort.
     */
    public void sort(){ word_count.sortWithCount(); }

    //Utility Methods
    String getSignificantWords()     { return Arrays.toString(word_count.getSigStrings()); }
    public String getSignificanceOfWords()  { return word_count.toString(); }
    int    getSignificantWordLength(){ return word_count.size(); }
}
