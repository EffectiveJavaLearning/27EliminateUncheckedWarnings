import java.util.Arrays;

/**
 * 解释如何用注解消除警告.
 * 在{@link #toArray(Object[])}方法中，显然elements未进行类型检验就进行强转，会警告。
 *
 * 注意对return语句使用@SuppressWarnings是不行的，因为return语句并不是声明语句;
 * 但是又不建议直接对整个方法使用该注解，因此需要将return语句拆开，先声明局部变量，后return，
 * 对声明语句使用注解，这样就ok了{@link EliminateUncheckedWarnings#toArray(Object[])},确认没有问题的代码会被正确地编译，
 * 而且作用范围最小化以防掩盖了其他有可能报warning的地方。
 *
 * @author LightDance
 * @date 2018/9/19
 */
class UseAnnotation {

    private String[] elements = {"a" , "sd" , "wer"};

/**方法头声明中使用了泛型*/

<T> T[] toArray(T[] a) {
    int size = 5;
    if (a.length < size) {
        return (T[]) Arrays.copyOf(elements, size, a.getClass());
    }
    System.arraycopy(elements, 0, a, 0, size);
    if (a.length > size) {
        a[size] = null;
    }
    return a;
}

    <T> T[] toArray2(T[] a) {
        int size = 3;
        if (a.length < size) {
            //我们创建的数组也是T类型，因此是安全的
            @SuppressWarnings("unchecked")
             T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
            return result;
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }


}
