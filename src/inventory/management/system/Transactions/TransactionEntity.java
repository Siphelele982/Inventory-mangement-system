package inventory.management.system.Transactions;

import java.sql.Timestamp;

public class TransactionEntity {
    private int transactionId;
    private int productId;
    private int userId;
    private String type; 
    private int quantity;
    private double totalPrice;
    private Timestamp date;

    public TransactionEntity(int transactionId, int productId, int userId, String type, int quantity,double totalPrice, Timestamp date) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.userId = userId;
        this.type = type;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }
    
    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }
    
    public int getTransactionId(){
        return transactionId;
    }
    
    public void setProductId(int productId){
        this.productId = productId;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public void setUserId(int userId){ 
        this.userId = userId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public  void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return type;  
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return  quantity;
    }
    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice(){
        return totalPrice;
    }
    
    public void setDate(Timestamp date){
        this.date = date;
    }
    
    public Timestamp getDate(){
        return date;
    }
}