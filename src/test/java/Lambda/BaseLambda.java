package Lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/2下午12:01
 * @描述
 */

public class BaseLambda {
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!!!");
            }
        };
        r1.run();
        Runnable r2 = () -> System.out.println("Hello Lambda!!!");
        r2.run();
    }

    @Test
    public void test2(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("0o0");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y)-> {
            System.out.println("learn lambda");
            return Integer.compare(x,y);
        };
    }

    @Test
    public void test4(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("aaa",30,5555),
            new Employee("eee",24,2435),
            new Employee("ccc",35,1643),
            new Employee("ddd",74,5134),
            new Employee("bbb",24,9447),
            new Employee("fff",56,4521)
    );
    @Test
    public void test5(){
        Collections.sort(employees,(e1,e2) -> {
            if (e1.getAge()==e2.getAge()){
                return -e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for (Employee employee:
             employees) {
            System.out.println(employee.toString());
        }
    }
}
