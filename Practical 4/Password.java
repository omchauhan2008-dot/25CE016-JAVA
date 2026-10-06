import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {

    private static Pattern lengthPattern  = Pattern.compile(".{8,}");
    private static Pattern upperPattern   = Pattern.compile("[A-Z]");
    private static Pattern digitPattern   = Pattern.compile("[0-9]");
    private static Pattern specialPattern = Pattern.compile("[^a-zA-Z0-9]");

    public static String strength(String pw) 
    {
        int count = 0;

        Matcher m1 = lengthPattern.matcher(pw);
        if (m1.find()) {
            System.out.println("  - Length >= 8: PASS");
            count++;
        } else {
            System.out.println("  - Length >= 8: FAIL");
        }

        Matcher m2 = upperPattern.matcher(pw);
        if (m2.find()) {
            System.out.println("  - Uppercase: PASS");
            count++;
        } else {
            System.out.println("  - Uppercase: FAIL");
        }

        Matcher m3 = digitPattern.matcher(pw);
        if (m3.find()) {
            System.out.println("  - Digit: PASS");
            count++;
        } else {
            System.out.println("  - Digit: FAIL");
        }

        Matcher m4 = specialPattern.matcher(pw);
        if (m4.find()) {
            System.out.println("  - Special Char: PASS");
            count++;
        } else {
            System.out.println("  - Special Char: FAIL");
        }

        if (count <= 1) {
            return "Weak";
        } else if (count <= 3) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    
    public static void main(String[] args)
     {
        String[] testPasswords = {"abc", "Abcd1234!"};

        for (String pw : testPasswords) {
            System.out.println("Password: " + pw);
            String result = strength(pw);
            System.out.println("Label: " + result);
            System.out.println("--------------------");
        }
    }
}