import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Significance {
    private HashMap<String, Integer> word_count;
    private ArrayList<String> common_words;

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
//    ArrayList<String> getUpToNthSigWord(int n) {
//        ArrayList<String> words = new ArrayList<>(n);
//
//        while (n <= 0){
//
//        }
//    }

    void cleanup() {
        word_count.remove("");
        word_count.entrySet().removeIf(kv -> common_words.contains(kv.getKey()));
    }

    void sortByValue() {
        Set<HashMap.Entry<String, Integer>> entries    = word_count.entrySet();
        ArrayList<HashMap.Entry<String, Integer>> list = new ArrayList<>(entries);
        LinkedHashMap<String, Integer> ordered_map     = new LinkedHashMap<>();

        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);
        for (Map.Entry<String, Integer> entry : list) ordered_map.put(entry.getKey(), entry.getValue());
        word_count = ordered_map;
    }

    //Utility Methods
    public String getSignificantWords()     { return word_count.keySet().toString(); }
    public String getSignificanceOfWords()  { return word_count.entrySet().toString(); }
    public int    getSignificantWordLength(){ return word_count.entrySet().size(); }
}
