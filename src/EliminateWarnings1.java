import java.util.HashSet;
import java.util.Set;

/**
 * 第一个消除warning的例子
 *
 * @author LightDance
 * @date 2018/9/19
 */
public class EliminateWarnings1 {

    /**
     * 这种写法会提示unchecked assignment, 但也很容易将警告消除，
     * 即用"<>"与HashSet配合使用。而且无需在<>中加任何类型参数，因为在Java7中，
     * 编译器会推断正确的实际参数类型并进行编译。(比如这里会把Integer填进去)
     *
     */
    private void warningStyle(){
        Set<Integer> integerSet = new HashSet();
        //这样就没问题了，类型会由编译器自动判断并填充
        Set<Integer> integerSet2 = new HashSet<>();
    }
}
