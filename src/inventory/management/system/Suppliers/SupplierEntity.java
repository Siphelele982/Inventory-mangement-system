package inventory.management.system.Suppliers;

public class SupplierEntity {
    private int supplierId;
    private String name;
    private String contactInfo;
    private String email;
    private String phone;

    public SupplierEntity(int supplierId, String name, String contactInfo,String email,String phone) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.email  = email;
        this.phone  = phone;
    }
    
    
    public void setSupplierId(int supplierId){
        this.supplierId = supplierId;
    }
    
    public int getSupplierId(){
        return supplierId;
    }
    
    public void setName(String name){
        this.name  = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }
    
    public String  getContactInfo(){
        return contactInfo;
 
    }
    public void setEmail(String  email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public String getPhone(){
        return phone;
    }
}

