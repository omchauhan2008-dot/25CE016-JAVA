package service;
import model.annotation.Id;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;

// Lab 13: Generic Repository
public class Repository<T> {
    private ConcurrentHashMap<String, T> store = new ConcurrentHashMap<>();

    public void save(T entity) {
        try {
            // FIX: We must check superclasses because @Id is in the Account class,
            // but the entity might be a SavingsAccount! getDeclaredFields() only checks the current class.
            Class<?> clazz = entity.getClass();
            while (clazz != null) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        field.setAccessible(true);
                        String id = (String) field.get(entity);
                        if (id != null) {
                            store.put(id, entity);
                        }
                        return; // Found and saved, exit the method
                    }
                }
                clazz = clazz.getSuperclass(); // Move up the inheritance chain
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public T findById(String id) { return store.get(id); }
    public List<T> findAll() { return new ArrayList<>(store.values()); }
    
    // Restore data from file
    public void loadAll(List<T> items) {
        if (items != null) {
            for(T item : items) { save(item); }
        }
    }
}
