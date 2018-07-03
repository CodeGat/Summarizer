package Significance;

import java.util.ArrayList;

public class BiMap {
    private ArrayList<Pair> pairs;

    public BiMap(){
        pairs = new ArrayList<>();
    }

    public void removeUsingSigstring(String key) {
        pairs.removeIf(p -> p.sigstring.equals(key));
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

    public void add(String sigstring, Integer count){ pairs.add(new Pair(sigstring, count)); }

    void increment(String sigstring) {
        for (Pair pair : pairs) {
            if (pair.sigstring.equals(sigstring)) {
                pair.count++;
            }
        }
    }

    /**
     * sorts n pairs by a custom
     */
    public void sortWithCount(){
        pairs.sort((p1, p2) -> {
            if (p1.count.equals(p2.count)) return 0;
            else if (p1.count.compareTo(p2.count) < 0) return 1;
            else return -1;
        });
    }

    public boolean containsSigstring(String sigstring) {
        for (Pair pair : pairs) {
            if (pair.sigstring.equals(sigstring)) return true;
        }
        return false;
    }

    /**
     * @return an array of the significant strings.
     */
    String[] getSigStrings() {
        String[] sigstrings = new String[pairs.size()];
        for (int i = 0; i < pairs.size(); i++) sigstrings[i] = pairs.get(i).sigstring;
        return sigstrings;
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

    //Utility or Unneeded Methods
    public boolean containsCount(Integer count) {
        for (Pair pair : pairs) if (pair.count.equals(count)) return true;
        return false;
    }

    public void sortWithSigstring(){
        pairs.sort((p1, p2)-> {
            if (p1.sigstring.equals(p2.sigstring)) return 0;
            else if (p1.sigstring.compareTo(p2.sigstring) < 0) return -1;
            else return 1;
        });
    }

    public Integer getCount(String sigstring){
        for (Pair pair : pairs) {
            if (pair.sigstring.equals(sigstring)) return pair.count;
        }
        return null;
    }

    // TODO: 30/06/2018 Gets only the first, could be more than one sigstring that occurs n times
    public String getSigstring(Integer count){
        for (Pair pair : pairs) if (pair.count.equals(count)) return pair.sigstring;
        return null;
    }

    public void removeUsingCount(Integer key) {
        pairs.removeIf(p -> p.count.equals(key));
    }
}