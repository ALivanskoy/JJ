import java.util.Random;

public class Employee {
    private static final String[] NAMES = {"John", "Emma", "Michael", "Sophia", "William", "Olivia"};
    private static final int MIN_AGE = 20;
    private static final int MAX_AGE = 60;
    private static final double MIN_SALARY = 1000.0;
    private static final double MAX_SALARY = 5000.0;
    private static final String[] DEPARTMENTS = {"HR", "Marketing", "IT", "Finance"};

    private String name;
    private int age;
    private double salary;
    private String department;

    public Employee() {
        Random random = new Random();
        this.name = NAMES[random.nextInt(NAMES.length)];
        this.age = MIN_AGE + random.nextInt(MAX_AGE - MIN_AGE + 1);
        this.salary = Math.round( (MIN_SALARY + (MAX_SALARY - MIN_SALARY) * random.nextDouble() ) * 100.0  ) / 100.0;
        this.department = DEPARTMENTS[random.nextInt(DEPARTMENTS.length)];
    }

    // Геттеры и сеттеры

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
