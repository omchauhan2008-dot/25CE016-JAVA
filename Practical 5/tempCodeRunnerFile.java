abstract class Employee {
    String name;
    int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    double fixedSalary;

    public FullTime(String name, int id, double fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    double monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    double hours;
    double rate;

    public PartTime(String name, int id, double hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    double stipend;

    public Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    double monthlySalary() {
        return stipend;
    }
}

public class Payroll {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Alice", 101, 5000),
            new PartTime("Bob", 102, 160, 15.50),
            new Intern("Charlie", 103, 1000),
            new FullTime("David", 104, 6000),
            new Intern("Emma", 105, 1200)
        };

        double totalSalary = 0;
        System.out.println("Payroll");
        for (Employee emp : employees) {
            double salary = emp.monthlySalary();
            System.out.print(emp.name + " (ID: " + emp.id + "):$" + salary);
            if (emp instanceof Intern) {
                System.out.print("(INTERN)");
            }
            System.out.println();
            totalSalary += salary;
        }
        System.out.println("Total Monthly Salary");
        System.out.println("$" + totalSalary);
    }
}