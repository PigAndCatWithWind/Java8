package Optional;

import Lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/12上午11:49
 * @描述
 * 1. Optional.of(T t) ：创建一个Optional实例
 * 2. Optional.empty()：创建一个空的Optional实例
 * 3. Optional.ofNullable(T t)：若t不为null，创建Optional实例，负责创建空实例
 * 4. isPresent()：判断是否包含值
 * 5. orElse(T t)：如果调用对象含值，返回该值，否则返回t
 * 6. orElseGet(Supplier s) ： 如果对象包含值，并返回处理后的Optional，否则返回s 获取值
 * 7. map(Function f)：如果有值对其处理，返回处理后的Optional，否则返回Optional.empty()
 * 8. flatMap(Function mapper)：与map类相似。要求返回值必须是Optional
 */
public class TestOptional {
    @Test
    public void test1(){
        Optional<Employee> optionalEmployee = Optional.of(new Employee());
        Employee employee = optionalEmployee.get();
        System.out.println(employee);
    }

    @Test
    public void test2(){
        Optional<Employee> optionalEmployee = Optional.empty();
        System.out.println(optionalEmployee.get());
    }

    @Test
    public void test3(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(null);
        if (optionalEmployee.isPresent()){
            System.out.println(optionalEmployee.get());
        }
    }

    @Test
    private void test4(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(null);
        Employee employee = optionalEmployee.orElse(new Employee("wangyi",58,9990, Employee.Status.BUSY));
        System.out.println(employee );
    }

    @Test
    public void test5(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(new Employee("wangyi",58,9990, Employee.Status.BUSY));
        Optional<Employee.Status> optionalS = optionalEmployee.map(employee -> employee.getStatus());
        System.out.println(optionalS.get());
    }

    @Test
    public void test6(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(new Employee("wangyi",58,9990, Employee.Status.BUSY));
        Optional<Integer> optionalInteger = optionalEmployee.flatMap(e -> Optional.of(e.getAge()));
        System.out.println(optionalInteger.get());
    }
}
