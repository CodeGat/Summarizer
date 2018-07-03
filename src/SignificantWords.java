import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SignificantWords {
    private BiMap word_count;
    private ArrayList<String> common_words;

    SignificantWords(){
        word_count   = new BiMap();
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
        if (word_count.containsSigstring(word)) {
            word_count.increment(word);
        } else {
            word_count.add(word, 1);
        }
    }

    String[] getUpToNthSigWord(int n) {
        String[] keys = word_count.getSigStrings();
        return Arrays.copyOf(keys, n);
    }

    void cleanup() {
        String[] sigstrs = word_count.getSigStrings();
        word_count.removeUsingSigstring("");
        for (String sigstr : sigstrs) {
            if (common_words.contains(sigstr)) {
                word_count.removeUsingSigstring(sigstr);
            }
        }
    }

    void sort(){ word_count.sortWithCount(); }

    //Utility Methods
    public String getSignificantWords()     { return Arrays.toString(word_count.getSigStrings()); }
    String getSignificanceOfWords()  { return word_count.toString(); }
    public int    getSignificantWordLength(){ return word_count.size(); }
}
