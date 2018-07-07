package Significance;

import java.util.ArrayList;

public class BiMap {
    private ArrayList<Pair> pairs;

    public BiMap(){
        pairs = new ArrayList<>();
    }

    private class Pair {
        String sigstring;
        Integer count;

        Pair(String sigstring, Integer count){
            this.sigstring = sigstring;
            this.count = count;
        }

        @Override
        public String toString(){
            return "<"+ sigstring +", "+ count.toString()+">";
        }
    }

    /**
     * @param sigstring a string that has occured at least once
     * @param count adding n significance to a string
     */
    public void add(String sigstring, Integer count){
        pairs.add(new Pair(sigstring, count));
    }

    /**
     * Increments a strings significance by 1
     * @param sigstring the string whose significance needs to be incremented.
     */
    void increment(String sigstring) {
        for (Pair pair : pairs) {
            if (pair.sigstring.equals(sigstring)) {
                pair.count++;
            }
        }
    }

    public void removeUsingSigstring(String key) {
        pairs.removeIf(p -> p.sigstring.equals(key));
    }

    public void removeUsingCount(Integer key) {
        pairs.removeIf(p -> p.count.equals(key));
    }

    public void sortWithCount(){
        pairs.sort((p1, p2) -> {
            if (p1.count.equals(p2.count)) return 0;
            else if (p1.count.compareTo(p2.count) < 0) return 1;
            else return -1;
        });
    }

    public void sortWithSigstring(){
        pairs.sort((p1, p2)-> {
            if (p1.sigstring.equals(p2.sigstring)) return 0;
            else if (p1.sigstring.compareTo(p2.sigstring) < 0) return -1;
            else return 1;
        });
    }

    public boolean containsSigstring(String sigstring) {
        for (Pair pair : pairs) {
            if (pair.sigstring.equals(sigstring)) return true;
        }
        return false;
    }

    public boolean containsCount(Integer count) {
        for (Pair pair : pairs) if (pair.count.equals(count)) return true;
        return false;
    }

    void trim(int n) {
        pairs.subList(n, pairs.size()).clear();
    }

    // accessor and misc. methods
    String[] getSigStrings() {
        String[] sigstrings = new String[pairs.size()];
        for (int i = 0; i < pairs.size(); i++) sigstrings[i] = pairs.get(i).sigstring;
        return sigstrings;
    }

    public Integer getCount(String sigstring){
        for (Pair pair : pairs) {
            if (pair.sigstring.equals(sigstring)) return pair.count;
        }
        return null;
    }

    /**
     * @param count the significance to look for.
     * @return the first sigstring with significance of the argument.
     */
    public String getSigstring(Integer count){
        for (Pair pair : pairs) if (pair.count.equals(count)) return pair.sigstring;
        return null;
    }

    int size() {
        return pairs.size();
    }

    @Override
    public String toString(){
        StringBuilder rtn = new StringBuilder();
        pairs.forEach(e -> rtn.append(e.toString()));
        return rtn.toString();
    }
}
