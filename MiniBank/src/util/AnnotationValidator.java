package util;
import model.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
public class AnnotationValidator {
    public static String[] validate(Object obj) {
        ArrayList<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.isAnnotationPresent(Positive.class)) {
                        long val = field.getLong(obj);
                        if (val <= 0) errors.add(field.getName() + " " + field.getAnnotation(Positive.class).message());
                    }
                } catch (Exception e) { }
            }
            clazz = clazz.getSuperclass();
        }
        return errors.toArray(new String[0]);
    }
}
