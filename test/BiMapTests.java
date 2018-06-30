import org.junit.Assert;
import org.junit.Test;

public class BiMapTests {

    @Test
    public void testAddPairs(){
        BiMap<String,Integer> biMap = new BiMap<>();

        biMap.add("Apple", 100);
        Assert.assertEquals("<Apple, 100>", biMap.toString());

        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);
        Assert.assertEquals("<Apple, 100><Cherry, 20><Banana, 99>", biMap.toString());
    }

    @Test
    public void testRemovePairs(){
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);

        biMap.removeWithFirst("Cherry");
        Assert.assertEquals("<Apple, 100><Banana, 99>", biMap.toString());
        biMap.removeWithSecond(100);
        Assert.assertEquals("<Banana, 99>", biMap.toString());
    }

    @Test
    public void testSortWithFirst(){
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);
        biMap.add("Zebra", 101);
        biMap.add("Foo", 59111);

        biMap.sortWithFirst();
        Assert.assertEquals("<Apple, 100><Banana, 99><Cherry, 20><Foo, 59111><Zebra, 101>", biMap.toString());
        biMap.sortWithFirst();
        Assert.assertEquals("<Apple, 100><Banana, 99><Cherry, 20><Foo, 59111><Zebra, 101>", biMap.toString());
    }

    @Test
    public void testSortWithSecond() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);
        biMap.add("Zebra", 101);
        biMap.add("Foo", 59111);

        biMap.sortWithSecond();
        Assert.assertEquals("<Cherry, 20><Banana, 99><Apple, 100><Zebra, 101><Foo, 59111>", biMap.toString());
        biMap.sortWithSecond();
        Assert.assertEquals("<Cherry, 20><Banana, 99><Apple, 100><Zebra, 101><Foo, 59111>", biMap.toString());
    }

    @Test
    public void testGetWith() {
        BiMap<String, Integer> biMap = new BiMap<>();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);

        Assert.assertEquals("20", biMap.getSecondWith("Cherry").toString());
        Assert.assertEquals("Apple", biMap.getFirstWith(100));
        Assert.assertNull(biMap.getSecondWith("Not Here"));
        Assert.assertNull(biMap.getFirstWith(0));
    }
}
