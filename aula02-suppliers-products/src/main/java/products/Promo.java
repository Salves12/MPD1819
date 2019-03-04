package products;

import suppliers.Supplier;

public class Promo implements Product{
    private Product product;
    private double Discount;
    private double newprice;

    public Promo (int Discount, Product product){
        this.product=product;
        this.Discount=1-(double)Discount/100;
        this.newprice=product.getPrice()*this.Discount;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getPrice() {
        return this.newprice;
    }

    @Override
    public int getPriceInCentimes() {
        return (int)product.getPrice()*100;
    }

    @Override
    public ProdType getType() {
        return product.getType();
    }

    @Override
    public double setPrice(double p) {
        return product.setPrice(p);
    }

    @Override
    public Supplier getSupplier() {
        return product.getSupplier();
    }

    @Override
    public void setSupplier(Supplier s) {
        product.setSupplier(s);
    }


    @Override
    public int compareTo( Product p ) {
        int res = this.getName().compareToIgnoreCase(p.getName());
        return res != 0 ? res
                : getPriceInCentimes() - p.getPriceInCentimes();
    }
}
