package Lambda;

/**
 * @创建人 PigAndCatWithWind
 * @创建时间 2021/2/2上午11:38
 * @描述
 */
public class FilterEmployeeByAge implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
