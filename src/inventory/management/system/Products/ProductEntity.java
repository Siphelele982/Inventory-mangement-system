package inventory.management.system.Products;
 
public class ProductEntity {
    private int productId;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private int supplierId;

    public ProductEntity(int productId, String name, String category, double price, int quantity, int supplierId) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }
    public void setProductId(int productId){
        this.productId = productId;
}
    public int getProductId(){
        return productId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public  String getCategory(){
        return category;
    }
    public void setPrice(double price ) {
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return quantity;
    }
    public  void  setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    public int getSupplierId(){
        return supplierId;
    }
}


