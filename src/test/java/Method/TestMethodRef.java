package Method;

import Lambda.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/6上午9:43
 * @描述
 *
 */
public class TestMethodRef {

//    对象::实例方法名
    @Test
    public void  test1(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("user method1");
        PrintStream printStream = System.out;
        consumer = printStream::println;
        consumer.accept("user method2");
        consumer = System.out::println;
        consumer.accept("user method3");
    }
    @Test
    public void test2(){
        Employee employee = new Employee();

        Supplier<String> stringSupplier = () ->employee.getName();
        System.out.println(stringSupplier.get());

        Supplier<Integer> integerSupplier = employee::getAge;
        System.out.println(integerSupplier.get());
    }

//    类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        comparator = Integer::compare;

    }

//    类::实例方法名
    @Test
    public void test4(){
        BiPredicate<String,String> biPredicate = (x,y) -> x.equals(y);
        biPredicate = String::equals;
    }
//    构造器引用
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();
        supplier = Employee::new;
    }

//    数组引用
    @Test
    public void test6(){
        Function<Integer, Integer[]> function = x -> new Integer[x];
        System.out.println(function.apply(10).length);

        function = Integer[] :: new;
        System.out.println(function.apply(10).length);

    }
}
