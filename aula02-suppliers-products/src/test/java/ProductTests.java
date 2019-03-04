import products.Drink;
import products.Promo;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ProductTests {
    @Test
    public void createPromoTest(){
        Drink Icetea= new Drink("Ice-tea",1);
        Promo p= new Promo(60,Icetea);
        assertEquals(0.4,p.getPrice());
    }
    @Test
    public void PromoZeroDiscount(){
        Drink Icetea= new Drink("Ice-tea",3);
        Promo p= new Promo(0,Icetea);
        assertEquals(3.0,p.getPrice());
    }
    @Test
    public void PromoFree(){
        Drink Icetea= new Drink("Ice-tea",3);
        Promo p= new Promo(100,Icetea);
        assertEquals(0.0,p.getPrice());
    }



}
