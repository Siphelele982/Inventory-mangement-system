package inventory.management.system.Users;

public class UserEntity {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String role;

    public UserEntity(int userId,String username,String password ,String email,String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role =  role;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return userId;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public String getRole(){
        return role;
    }
    
}
