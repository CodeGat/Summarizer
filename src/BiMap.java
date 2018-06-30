import java.util.ArrayList;

public class BiMap {
    private ArrayList<Pair> pairs;

    BiMap(){
        pairs = new ArrayList<>();
    }

    void removeUsingSigstring(String key) {
        pairs.removeIf(p -> p.sigstring.equals(key));
    }

    void removeUsingCount(Integer key) {
        pairs.removeIf(p -> p.count.equals(key));
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

    void add(String sigstring, Integer second){ pairs.add(new Pair(sigstring, second)); }

    void increment(String sigstring) {
        for (Pair pair : pairs) if (pair.sigstring.equals(sigstring)) pair.count = pair.count++;
    }

    Integer getSecondWith(String sigstring){
        for (Pair pair : pairs) if (pair.sigstring.equals(sigstring)) return pair.count;
        return null;
    }

    String getFirstWith(Integer count){
        for (Pair pair : pairs) if (pair.count.equals(count)) return pair.sigstring;
        return null;
    }

    void sortWithFirst(){
        pairs.sort((p1, p2)-> {
            if (p1.sigstring.equals(p2.sigstring)) return 0;
            else if (p1.sigstring.compareTo(p2.sigstring) < 0) return -1;
            else return 1;
        });
    }

    void sortWithSecond(){
        pairs.sort((p1, p2) -> {
            if (p1.count.equals(p2.count)) return 0;
            else if (p1.count.compareTo(p2.count) < 0) return -1;
            else return 1;
        });
    }

    boolean containsFirst(String sigstring) {
        for (Pair pair : pairs) if (pair.sigstring.equals(sigstring)) return true;
        return false;
    }

    boolean containsSecond(Integer count) {
        for (Pair pair : pairs) if (pair.count.equals(count)) return true;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder rtn = new StringBuilder();
        pairs.forEach(e -> rtn.append(e.toString()));
        return rtn.toString();
    }
}
