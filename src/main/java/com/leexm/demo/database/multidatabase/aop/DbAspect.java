package com.leexm.demo.database.multidatabase.aop;

import com.alibaba.druid.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author leexm
 * @date 2020/7/8 13:12
 */
@Component
@Aspect
public class DbAspect {

    @Resource
    private JdbcTemplate masterJdbcTemplate;

    @Resource
    private JdbcTemplate salveJdbcTemplate;

    @Pointcut(value = "@annotation(com.leexm.demo.database.multidatabase.aop.DS)")
    private void database() {
    }

    @Around(value = "database() && @annotation(ds)")
    public Object aroundDao(ProceedingJoinPoint joinPoint, DS ds) throws Throwable {
        String db = ds.db();
        Object[] args = joinPoint.getArgs();
        JdbcTemplate jdbcTemplate = StringUtils.equals("salve", db) ? salveJdbcTemplate : masterJdbcTemplate;
        args[args.length - 1] = jdbcTemplate;
        return joinPoint.proceed(args);
    }

}
