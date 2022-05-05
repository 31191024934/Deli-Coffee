package project.edu.example.delicoffee.model;

public class Users {
         public String username,email,sdt;
         private Cart cart;
    public Users() {
    }
    public Users(String username, String email,String sdt) {
        this.username = username;
        this.email = email;
        this.sdt = sdt;
    }
    public Users(String username, String email) {
        this.username = username;
        this.email = email;

    }

    public Users(String username, String email, String sdt, Cart cart) {
        this.username = username;
        this.email = email;
        this.sdt = sdt;
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
