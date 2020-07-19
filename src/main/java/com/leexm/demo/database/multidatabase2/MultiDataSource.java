package com.leexm.demo.database.multidatabase2;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author leexm
 * @date 2020/7/8 15:44
 */
public class MultiDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDb();
    }

}
