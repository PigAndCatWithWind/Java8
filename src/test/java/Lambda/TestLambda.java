package Lambda;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/2上午11:14
 * @描述
 */
public class TestLambda {
    @Test
    public void test1(){
        Comparator<Integer> comparator =
                new Comparator<Integer>() {
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o1,o2);
                    }
                };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    @Test
    public void test2(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("aaa",18,9999.9),
            new Employee("bbb",20,777.9),
            new Employee("ccc",38,5555),
            new Employee("ddd",50,6666),
            new Employee("eee",37,99.9)
    );

    public List<Employee> findEmployees(List<Employee> list){
        List<Employee> employees = new ArrayList<>();
        for(Employee temp : list){
            if (temp.getAge() >= 35){
                employees.add(temp);
            }
        }
        return employees;
    }

    @Test
    public void teat3(){
        List<Employee> employeeList =
                findEmployees(employees);
        for (Employee employee:
             employeeList) {
            System.out.println(employee.toString());
        }
    }

    public List<Employee> filterEmployees(List<Employee> employees,MyPredicate<Employee> employeeMyPredicate){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee temp:
             employees) {
            if (employeeMyPredicate.test(temp)){
                employeeList.add(temp);
            }
        }
        return employeeList;
    }

    @Test
    public void test4(){
        List<Employee> employeeList =
                filterEmployees(employees,new FilterEmployeeByAge());
        for (Employee employee:
             employeeList) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test5(){
        List<Employee> employeeList =
                filterEmployees(employees, new MyPredicate<Employee>() {
                    @Override
                    public boolean test(Employee employee) {
                        return employee.getAge() >= 35;
                    }
                });
        for (Employee employee:
             employeeList) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test6(){
        List<Employee> employeeList =
                filterEmployees(employees,(employee)-> employee.getSalary() > 5555);
        for (Employee employee:
             employeeList) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void test7(){
        employees.stream()
                .filter((e) -> e.getAge() < 20)
                .forEach(System.out::println);
    }
}
