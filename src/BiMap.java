import java.util.ArrayList;

public class BiMap<T, S> {
    private ArrayList<Pair> pairs;

    BiMap(){
        pairs = new ArrayList<>();
    }

    void removeWithFirst(T key) {
        pairs.removeIf(p -> p.first.equals(key));
    }

    void removeWithSecond(S key) {
        pairs.removeIf(p -> p.second.equals(key));
    }

    private class Pair {
        T first;
        S second;

        Pair(T first, S second){
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString(){
            return "<"+first.toString()+", "+second.toString()+">";
        }
    }

    void add(T first, S second){
        pairs.add(new Pair(first, second));
    }

    S getSecondWith(T first){
        for (Pair pair : pairs) if (pair.first == first) return pair.second;
        return null;
    }

    T getFirstWith(S second){
        for (Pair pair : pairs) if (pair.second == second) return pair.first;
        return null;
    }

    void sortWithFirst(){
        pairs.sort((p1, p2) -> {
            if (p1.first == p2.first) return 0;
            else if (p1.first instanceof Integer && p2.first instanceof Integer) {
                if ((Integer)p1.first < (Integer)p2.first) return -1;
                else return 1;
            } else if (p1.first instanceof String && p2.first instanceof String) {
                if (((String) p1.first).compareTo((String) p2.first) < 0) return -1;
                else return 1;
            } else return -2;
        });
    }

    void sortWithSecond(){
        pairs.sort((p1, p2) -> {
            if (p1.second == p2.second) return 0;
            else if (p1.second instanceof Integer && p2.second instanceof Integer) {
                if ((Integer)p1.second < (Integer)p2.second) return -1;
                else return 1;
            } else if (p1.second instanceof String && p2.second instanceof String) {
                if (((String) p1.second).compareTo((String) p2.second) < 0) return -1;
                else return 1;
            } else return -2;
        });
    }

    @Override
    public String toString(){
        StringBuilder rtn = new StringBuilder();
        pairs.forEach(e -> rtn.append(e.toString()));
        return rtn.toString();
    }
}
