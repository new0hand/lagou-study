import com.lagou.edu.utils.ClassUtils;
import org.junit.Test;

import java.util.Set;

/**
 * @Author: zhuhf
 * @Date: 2020/4/13 4:18 上午
 */
public class TestClassUtils {
    @Test
    public static void main(String[] args) {
        Set<Class<?>> classSet = ClassUtils.getClassSet("com.lagou.edu");
        classSet.forEach(aClass -> {
            System.out.println(aClass);
        });

    }
}
