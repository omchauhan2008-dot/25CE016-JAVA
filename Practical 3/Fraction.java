import java.util.Objects;

public class Fraction {
    private int num;
    private int den;

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public Fraction(int num, int den) {
        int g = gcd(num, den);
        this.num = num / g;
        this.den = den / g;
    }

    public String toString() {
        return num + "/" + den;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return num == fraction.num && den == fraction.den;
    }

    public int hashCode() {
        return Objects.hash(num, den);
    }
}