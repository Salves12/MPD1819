import org.junit.Test;
import products.*;
import suppliers.OneProductProducer;
import suppliers.Producer;
import suppliers.Retailer;
import suppliers.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SuppliersTests {

    private final static Store store;

    static {
        List<Supplier>  suppliers  = new ArrayList<>();
        suppliers.add(new OneProductProducer("Manel", new Food("Morangos", 3.0)));
        suppliers.add(new OneProductProducer("Joaquim", new Food("Pera rocha", 2.0)));

        Supplier cocaCola = new Producer(
                "Coca-Cola company",
                    new Drink("Coca-Cola", 1.0),
                    new Drink("Coca-Cola Zero", 1.2));

        Supplier pepsiCola = new Producer(
                "Pepsi company",
                new Drink("Pepsi", 1.0),
                new Drink("PepsiZero", 1.5));

        suppliers.add( new Retailer("Cola importer")
                .add(cocaCola)
                .add(pepsiCola));

        Supplier moulinex = new Producer(
                "Moulinex",
                new Electronic("Robot", 300),
                new Electronic("Toaster 500", 30),
                new Electronic("Toaster1000", 50));

        suppliers.add(moulinex);

        Supplier ajax = new Producer("Ajax",
                new Cleaning("Limpa-vidros", 2),
                new Cleaning("Esfregão", 1.3));

        suppliers.add(ajax);

        store = new Store(suppliers.toArray(new Supplier[0]));

    }


    @Test
    public void countSuppliersTest() {
        int expectedSuppliers = 7;
        assertEquals(expectedSuppliers, store.getAllSuppliers().length);
    }

    @Test
    public void countProductsTest() {
        int expectedProducts = 11;
        assertEquals(expectedProducts, store.getAllProducts().length);
    }
    @Test
    public void highPriceTest(){

        Map<Product.ProdType,Product> res=store.mostExpensiveByType();
        String namedrink="PepsiZero";
        String nameeletronic="Robot";
        assertEquals(namedrink,(( res.get(Product.ProdType.DRINK)).getName()));
        assertEquals(nameeletronic,(( res.get(Product.ProdType.ELECTRONIC)).getName()));
    }

    @Test
    public void fullHighPriceTest(){

        Map<Product.ProdType,Product> res=store.mostExpensiveByType();
        String namedrink="PepsiZero";
        String nameeletronic="Robot";
        assertEquals(namedrink,(( res.get(Product.ProdType.DRINK)).getName()));
        assertEquals(nameeletronic,(( res.get(Product.ProdType.ELECTRONIC)).getName()));
    }

    @Test
    public void fullMapTest(){
        Map<Product.ProdType, Product> res= new TreeMap<>();
        res.put(Product.ProdType.DRINK,new Drink("PepsiZero", 1.5));
        res.put(Product.ProdType.ELECTRONIC,new Electronic("Robot", 300));
        res.put(Product.ProdType.DRUGSTORE,new Cleaning("Limpa-vidros", 2));
        res.put(Product.ProdType.FOOD, new Food("Morangos", 3.0));


        assertTrue(store.mostExpensiveByType().equals(res));
    }

}
