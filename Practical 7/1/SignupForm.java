
public class SignupForm {

    @NotBlank
    @MaxLength(10)
    String username;

    @NotBlank
    @MaxLength(30)
    String email;

    public SignupForm(String username, String email) {
        this.username = username;
        this.email = email;
    }
}