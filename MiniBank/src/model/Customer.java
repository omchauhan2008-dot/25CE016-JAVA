package model;
import model.annotation.Id;
import java.io.Serializable;
public class Customer implements Serializable, Cloneable {
    @Id
    private final String customerId;
    private String name, email, mobile;
    private Address address;
    private static long customerCounter = 100;
    
    public Customer(String name, String email, String mobile) {
        this.customerId = "CUST" + (++customerCounter);
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
    
    public String getCustomerId() { return customerId; }
    
    @Override
    public Customer clone() {
        try { return (Customer) super.clone(); }
        catch (CloneNotSupportedException e) { return null; }
    }
    
    // Static nested class (Lab 3)
    public static class Address implements Serializable {
        public String line, city, pincode;
        public Address(String line, String city, String pincode) {
            this.line = line; this.city = city; this.pincode = pincode;
        }
    }
}
