package Lambda;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/2上午11:22
 * @描述
 */
public class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(){

    }

    public Employee(String name,int age,double salary){
        super();
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
