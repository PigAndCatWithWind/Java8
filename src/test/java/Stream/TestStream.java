package Stream;

import Lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
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
            new Employee("zhangfei",18,9999.99, Employee.Status.BUSY),
            new Employee("zhangfei",18,9999.99,Employee.Status.FREE),
            new Employee("lisi2",58,5555.55,Employee.Status.VOCATION),
            new Employee("lisi1",58,5555.55,Employee.Status.FREE),
            new Employee("wuangwu",26,3333.33,Employee.Status.VOCATION),
            new Employee("liuliu",36,6666.66, Employee.Status.BUSY),
            new Employee("liuliu",36,6666.66, Employee.Status.VOCATION),
            new Employee("qianqi",12,8888.88, Employee.Status.BUSY)
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


//    map ---> 接受Lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
//    flatMap ---> 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    List<String> stringList = Arrays.asList("eee1","aaa","zzz","ddd","eee2");
    @Test
    public void test7(){
        stringList.stream()
                .map(TestStream::filterCharacter)
                .forEach(s -> s.forEach(System.out::println));
    }

    @Test
    public void test8(){
        stringList.stream()
                .flatMap(TestStream::filterCharacter)
                .forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String s){
        List<Character> list = new ArrayList<>();
        for (Character c: s.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

//    1. sorted() ---> 自然排序 Comparable
//    2. sorted(Comparator com) ---> 定制排序
    @Test
    public void test9(){
        stringList.stream()
                .sorted()
                .forEach(System.out::println);
    }
    @Test
    public void test10(){
        employees.stream()
                .sorted((e1,e2) -> {
                    if (e1.getAge()==e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else {
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }
                }).forEach(System.out::println);
    }

    /**
     *终止操作
     * 1. allMatch ---> 检查是否匹配所有元素
     * 2. anyMatch ---> 检查是否至少匹配一个元素
     * 3. noneMatch ---> 检查是否没有匹配所有元素
     * 4. findFirst ---> 返回第一个元素
     * 5. findAny ---> 返回当前流这中任意元素
     * 6. count ---> 返回流中元素的总个数
     * 7. max ---> 返回其中最大值
     * 8. min ---> 返回其中最小值
     */
    @Test
    public void test11(){
        boolean b = employees.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
    }

    @Test
    public void test12(){
        System.out.println(
                employees.stream()
                .anyMatch(
                        employee -> employee.getStatus().equals(Employee.Status.VOCATION)
                )
        );
    }

    @Test
    public void test13(){
        System.out.println(
                employees.stream()
                        .noneMatch(
                                employee -> employee.getStatus().equals(Employee.Status.BUSY)
                        )
        );
    }

    @Test
    public void test14(){
        System.out.println(
                employees.stream()
                .sorted((e1,e2) ->{
                    if (e1.getSalary()==e2.getSalary()){
                        return Integer.compare(e1.getAge(),e2.getAge());
                    }else {
                        return -Double.compare(e1.getSalary(),e2.getSalary());
                    }
                }).findFirst()
                        .get()
                        .toString()
        );
    }

    @Test
    public void test15(){
        Optional<Employee> optionalEmployee= employees.stream()
                .filter(employee -> employee.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(optionalEmployee.get());
    }

    @Test
    public void test16(){
        System.out.println(
                employees.stream()
                .count()
        );
    }

    @Test
    public void test17(){
        System.out.println(
                employees.stream()
                .max((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))
                .get()
        );
    }

    @Test
    public void test18(){
        System.out.println(
                employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare)
                .get()
        );
    }

    /**
     *归约
     *
     * reduce(T identity , BinaryOperator) / reduce(BinaryOperator) ---> 可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test19(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        int sum = list.stream()
                .reduce(0,(x,y) -> x+y);
        System.out.println(sum);
    }
    @Test
    public void test20(){
        Optional<Double> optionalDouble =
                employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(optionalDouble.get());
    }

    /**
     *  收集
     *
     * collect ---> 将流转化为其他形式，接收一个Collector接口的实现类，用于给Stream中元素汇总的方法
     */

    @Test
    public void test21(){
        List<String> stringList =
                employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(stringList);
    }

    @Test
    public void test22(){
        HashSet<String> stringHashSet =
                employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        stringHashSet.forEach(System.out::println);
    }

    @Test
    public void test23(){
        Double d = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(d);
    }

    @Test
    public void  test24(){
        Optional<Employee> optionalEmployee =
                employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(optionalEmployee.get());
    }

    @Test
    public void test25(){
        Optional<Double> optionalDouble =
                employees.stream()
                        .map(Employee::getSalary).min(Double::compare);
        System.out.println(optionalDouble.get());
    }

    @Test
    public void test26(){
        Map<Employee.Status,List<Employee> > statusListMap =
                employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(statusListMap);
    }

    @Test
    public void test27(){
        Map<Employee.Status,Map<String, List<Employee> > > mapMap =
                employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy((Employee e) -> {
                    if (e.getAge()<=35){
                        return "yong";
                    }else if (e.getAge()<=50){
                        return "zhong";
                    }else{
                        return "old";
                    }
                })));
        System.out.println(mapMap);
    }

    @Test
    public void test28(){
        Map<Boolean,List<Employee>> listMap =
                employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary()>8000));
        System.out.println(listMap);
    }

    @Test
    public void test29(){
      DoubleSummaryStatistics doubleSummaryStatistics =
              employees.stream()
                      .collect(Collectors
                              .summarizingDouble(Employee::getSalary));
      System.out.println(doubleSummaryStatistics.getAverage());
      System.out.println(doubleSummaryStatistics.getMax());
      System.out.println(doubleSummaryStatistics.getCount());
      System.out.println(doubleSummaryStatistics.getMin());
      System.out.println(doubleSummaryStatistics.getSum());
    }

    @Test
    public void test30(){
        String s = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(" ---> "));
        System.out.println(s);
    }
}
