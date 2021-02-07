package Stream;

import Lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/7上午10:20
 * @描述
 */
public class TestStream {
//    Stream 创建
    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        Stream<String> stringStream = list.stream();

        Employee[] employee = new Employee[10];
        Stream<Employee> employeeStream = Arrays.stream(employee);

        Stream<String> stream = Stream.of("a","bb","ccc");

        Stream<Integer> integerStream = Stream.iterate(0,x -> x+2);
        integerStream.forEach(System.out::println);

        Stream<Double> doubleStream = Stream.generate(() -> Math.random());
        doubleStream.forEach(System.out::println);
    }
//    中间操作
    /**
      *@创建人 PigAndCatWithWind
      *@创建时间 2021/2/7
      *@时间 上午10:44
      *@描述
     * 1. ilter ---> 接受Lambda，从流中排除某些元素
     * 2. limit ---> 截断流，使其元素不超过给定数量
     * 3. skip(n) ---> 跳过元素，返回一个仍掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
     * 4. distinct ---> 筛选，通过流所生成元素的hashCode()和equals() 去除重复元素
     */
    List<Employee> employees = Arrays.asList(
            new Employee("zhangfei",18,9999.99),
            new Employee("zhangfei",18,9999.99),
            new Employee("lisi",58,5555.55),
            new Employee("lisi",58,5555.55),
            new Employee("wuangwu",26,3333.33),
            new Employee("liuliu",36,6666.66),
            new Employee("liuliu",36,6666.66),
            new Employee("qianqi",12,8888.88)
    );
    @Test
    public void test2(){
        employees.stream()
                .filter((e) -> e.getAge() > 35)
                .forEach(System.out::println);
    }
    @Test
    public void test3(){
        Stream<Employee> employeeStream =
                employees.stream().filter(e -> e.getAge() >35);
        Iterator<Employee> iterator = employeeStream.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }

    @Test
    public void test4(){
        employees.stream()
                .filter((e) -> e.getSalary() > 5000)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test5(){
        employees.stream()
                .filter((e) -> e.getSalary()>5000)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test6(){
        employees.stream()
                .distinct()
                .forEach(System.out::println);
    }



}
