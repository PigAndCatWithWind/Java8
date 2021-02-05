package Lambda;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/2上午11:36
 * @描述
 */
@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
