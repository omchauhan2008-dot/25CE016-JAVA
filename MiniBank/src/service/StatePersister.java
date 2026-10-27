package service;
import model.Account;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

// Lab 11: Serialization
public class StatePersister {
    public static void save(List<Account> accounts, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(accounts);
        } catch (IOException e) { System.out.println("No previous data to save or error occurred."); }
    }

    @SuppressWarnings("unchecked")
    public static List<Account> load(String filename) {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Account>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
