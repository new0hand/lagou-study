package com.lagou.edu.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @Author: zhuhf
 * @Date: 2020/4/22 5:54 下午
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {
    String[] value() default {};
}
