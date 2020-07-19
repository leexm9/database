package com.leexm.demo.database.multidatabase2.aop;

import java.lang.annotation.*;

/**
 * @author leexm
 * @date 2020/7/8 11:57
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DS {

    String db() default "master";

}
