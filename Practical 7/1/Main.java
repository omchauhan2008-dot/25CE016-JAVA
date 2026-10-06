import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) {

        SignupForm form =
                new SignupForm("test12345566543", "abc@gmail.com");

        Field[] fields =
                form.getClass().getDeclaredFields();

        for (Field field : fields) {

            try {

                field.setAccessible(true);

                String value =
                        (String) field.get(form);

                // Check @NotBlank
                if (field.isAnnotationPresent(NotBlank.class)) {

                    if (value == null ||
                            value.trim().isEmpty()) {

                        System.out.println(
                                field.getName()
                                        + " : Cannot be blank"
                        );
                    }
                }

                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength annotation =
                            field.getAnnotation(MaxLength.class);

                    int max =
                            annotation.value();

                    if (value != null &&
                            value.length() > max) {

                        System.out.println(
                                field.getName()
                                        + " : Maximum length is "
                                        + max
                        );
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}