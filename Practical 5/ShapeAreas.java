abstract class Shape{
    abstract void area();
}
class Circle extends Shape{
    double r;
    Circle(double r){
        this.r=r;
    }
    void area() {
        double a= 3.14*r*r;
        System.out.println("Circle Area:"+a);
    }
}
class Rectangle extends Shape{
    double l;
    double w;
    Rectangle(double l,double w){
        this.l=l;
        this.w=w;
    }
    void area() {
        double a= l*w;
        System.out.println("Rectangle Area:"+a);
    }
}
class Triangle extends Shape{
    double b;
    double h;
   Triangle(double b,double h){
        this.b=b;
        this.h=h;
    }
    void area() {
        double a= 0.5*b*h;
        System.out.println("Triangle Area:"+a);
    }
}
public class ShapeAreas {
    public static void main(String[] args) {
        Shape[] shapes={
            new Circle(5),
            new Rectangle(10,5),
            new Triangle (2,6)
        };
        for (Shape s1: shapes){
            s1.area();
        }
    }
}