
import products.*;

import suppliers.Supplier;


import java.util.*;

/**
 * A container for suppliers and it's products
 */
public class Store {
    private List<Product> catalog;
    private Supplier[] suppliers;

    private final static Product[] emptyProducts = new Product[0];
    private final static Supplier[] emptySuppliers = new Supplier[0];

    public Store(Supplier ... suppliers) {
        this.suppliers = suppliers;
        catalog = new LinkedList<>();
        for(Supplier s : suppliers) {
            for(Product p : s.getProducts()) catalog.add(p);
        }
    }

    // queries from store

    public Product[] getAllElectronics() {
        List<Product> prods = new ArrayList<>();
        for(Product p: catalog) {
            if (p.getType() == Product.ProdType.ELECTRONIC) prods.add(p);
        }
        return prods.toArray(emptyProducts);
    }

    public Supplier[] getAllSuppliers() {
        Map<Supplier, Boolean> suppliersMap = new HashMap<>();
        for(Product p: catalog) {
            Supplier s = p.getSupplier();
            suppliersMap.computeIfAbsent(s, sup -> true );
        }
        for(Supplier s: suppliers) {
            suppliersMap.computeIfAbsent(s, sup -> true );
        }
        return suppliersMap.keySet().toArray(emptySuppliers);
    }

    public Product[] getAllProducts() {
        return catalog.toArray(emptyProducts);
    }


    public Map <Product.ProdType, Product> mostExpensiveByType() {

        Map<Product.ProdType,Product> res = new TreeMap<>();

        for (Product d : catalog) {
            if (!res.containsKey(d.getType())) {
                res.put(d.getType(), d);
            }
            else {
                if (d.getPriceInCentimes() > res.get(d.getType()).getPriceInCentimes()) {
                    res.remove(d.getType());
                    res.put(d.getType(), d);
                }
            }
        }
        return res;
    }
    private static class ProductCmpByPrice implements Comparator<Product>{
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getPriceInCentimes()-o2.getPriceInCentimes();

        }
    }



}

