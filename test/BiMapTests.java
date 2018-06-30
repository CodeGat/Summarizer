import org.junit.Assert;
import org.junit.Test;

public class BiMapTests {

    @Test
    public void testAddPairs(){
        BiMap biMap = new BiMap();

        biMap.add("Apple", 100);
        Assert.assertEquals("<Apple, 100>", biMap.toString());

        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);
        Assert.assertEquals("<Apple, 100><Cherry, 20><Banana, 99>", biMap.toString());
    }

    @Test
    public void testRemovePairs(){
        BiMap biMap = new BiMap();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);

        biMap.removeUsingSigstring("Cherry");
        Assert.assertEquals("<Apple, 100><Banana, 99>", biMap.toString());
        biMap.removeUsingCount(100);
        Assert.assertEquals("<Banana, 99>", biMap.toString());
    }

    @Test
    public void testSortWithFirst(){
        BiMap biMap = new BiMap();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);
        biMap.add("Zebra", 101);
        biMap.add("Foo", 59111);

        biMap.sortWithSigstring();
        Assert.assertEquals("<Apple, 100><Banana, 99><Cherry, 20><Foo, 59111><Zebra, 101>", biMap.toString());
        biMap.sortWithSigstring();
        Assert.assertEquals("<Apple, 100><Banana, 99><Cherry, 20><Foo, 59111><Zebra, 101>", biMap.toString());
    }

    @Test
    public void testSortWithSecond() {
        BiMap biMap = new BiMap();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);
        biMap.add("Zebra", 101);
        biMap.add("Foo", 59111);

        biMap.sortWithCount();
        Assert.assertEquals("<Cherry, 20><Banana, 99><Apple, 100><Zebra, 101><Foo, 59111>", biMap.toString());
        biMap.sortWithCount();
        Assert.assertEquals("<Cherry, 20><Banana, 99><Apple, 100><Zebra, 101><Foo, 59111>", biMap.toString());
    }

    @Test
    public void testGetWith() {
        BiMap biMap = new BiMap();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);

        Assert.assertEquals("20", biMap.getCount("Cherry").toString());
        Assert.assertEquals("Apple", biMap.getSigstring(100));
        Assert.assertNull(biMap.getCount("Not Here"));
        Assert.assertNull(biMap.getSigstring(0));
    }

    @Test
    public void testContains(){
        BiMap biMap = new BiMap();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
        biMap.add("Banana", 99);

        Assert.assertTrue(biMap.containsSigstring("Cherry"));
        Assert.assertTrue(biMap.containsCount(20));
        Assert.assertFalse(biMap.containsSigstring("Nothing Here"));
        Assert.assertFalse(biMap.containsCount(9001));
    }

    @Test
    public void testUpdate() {
        BiMap biMap = new BiMap();
        biMap.add("Apple", 100);
        biMap.add("Cherry", 20);
    }
}
