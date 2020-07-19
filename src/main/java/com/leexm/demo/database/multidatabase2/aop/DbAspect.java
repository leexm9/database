package com.leexm.demo.database.multidatabase2.aop;

import com.leexm.demo.database.multidatabase2.DbContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author leexm
 * @date 2020/7/8 13:12
 */
@Component
@Aspect
public class DbAspect {

    @Pointcut(value = "@annotation(com.leexm.demo.database.multidatabase2.aop.DS)")
    private void database() {
    }

    @Before("database() && @annotation(ds)")
    public void beforeDao(JoinPoint joinPoint, DS ds) {
        String db = ds.db();
        DbContextHolder.setDb(db);
    }

    @After("database()")
    public void afterDao() {
        DbContextHolder.clearDb();
    }

}
