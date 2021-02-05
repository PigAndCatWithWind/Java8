package Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/5下午7:47
 * @描述
 * Consumer<T> :
 *      void accept(T t);
 * Supplier<T> :
 *      T get();
 * Function<T , R> :
 *      R apply(T t);
 * Predicate<T> :
 *      boolean test(T t);
 */
public class kernelLambda {
//    Consumer
    public void happy(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }
    @Test
    public void test1(){
        happy(1000.0,x -> System.out.println(" you cousumer : " + x +" $"));
    }

//    Supplier
    public List<Integer> getIntegerList(int num, Supplier<Integer> supplier){
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i <num ; i++) {
            integerList.add(supplier.get());
        }
        return integerList;
    }
    @Test
    public void test2(){
        List<Integer> integerList = getIntegerList(10,()->(int)(Math.random()*100));
        for (Integer integer:
             integerList) {
            System.out.println(integer);
        }
    }

//    Function
    public String strHandler(String string, Function<String, String> stringFunction){
        return stringFunction.apply(string);
    }
    @Test
    public void test3(){
        String string =
                strHandler("okko",s -> s.substring(1,3));
        System.out.println(string);
    }

//    Predicate
    public List<String> StringList(List<String> stringList,Predicate<String> predicate){
        List<String> list = new ArrayList<>();
        for (String s:
             stringList) {
            if (predicate.test(s)){
                list.add(s);
            }
        }
        return list;
    }
    @Test
    public void test4(){
        List<String> list = Arrays.asList("aasadva","advbs","vsdvca");
        List<String> stringList = StringList(list,s -> s.length()>5);
        for (String s:
             stringList) {
            System.out.println(s);
        }
    }
}
