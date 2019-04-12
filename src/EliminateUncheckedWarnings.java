import java.util.Arrays;

/**
 * 使用泛型时，总会出现好多关于转换和类型匹配的警告。写得多了，警告也会渐渐变少。
 * 很多unchecked warnings是很容易消除的，比如这个例子：{@link EliminateWarnings1}。
 * 但并不是所有警告都这么容易消除，当遇到需要思考的警告时，需要特别注意并坚持将一切
 * unchecked warnings消除掉，以保证类型安全(type-safe).这样你的代码在运行时就不会扔
 * {@link ClassCastException},并且按你指定的逻辑运行。
 *
 * 万一你不能够消除警告，但有绝对的把握证明这段代码不会出问题，当且仅当这样的情况下，
 * 可以通过@SuppressWarnings("unchecked")注解来抑制警告。但是，万一你证明不了，那么就简直是在给自己挖坑，
 * 因为也没啥提示，但运行时却偏偏炸了。反之，如果你完全安全的地方你没加SuppressWarnings注解，
 * 那么新出现的、真正造成问题的警告就容易被淹没在n多“安全”的警告提示之中。
 *
 * 这个@SuppressWarnings 注解可以修饰很多声明语句，从变量声明到类声明都可以用它。
 * 因此要尽量保持该注解的作用范围最小，通常是对变量或者很短的方法使用它。另外千万别对整个类使用这个注解，
 * 这样会掩盖太多有价值的warning信息。如果需要对一个比较长的方法使用这个注解，那么考虑声明局部变量，
 * 然后将对整个方法起作用的注解移动到方法内部，缩小其作用范围。{@link UseAnnotation#toArray(Object[])}
 *
 * 另外，要养成"每次使用@SuppressWarnings注解时同时附加注释说明"的好习惯，以解释为什么这样做是安全的。
 * 一方面当你感觉很难说明清楚的时候，很可能这个地方并不是“绝对安全”的，另一方面，
 * 这样做也可以帮助其他人理解代码，以防他们瞎吉尔改你的代码令计算不安全。
 *
 * 总之，不要忽略任何unchecked warnings, 它们都有运行时抛出{@link ClassCastException}的可能性，
 * 需要考虑如何消除它们，或在保证没有问题的前提下使用@SuppressWarnings注解+注释说明。
 *
 * @author LightDance
 * @date 2018/9/19
 */
class EliminateUncheckedWarnings {

    private String[] elements = {"a" , "sd" , "wer"};


    <T> T[] toArray(T[] a) {
        int size = 5;
        if (a.length < size) {
            // This cast is correct because the array we're creating
            // is of the same type as the one passed in, which is T[].
            @SuppressWarnings("unchecked") T[] result =
                    (T[]) Arrays.copyOf(elements, size, a.getClass());
            return result;
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }


}
