package com.leexm.demo.database.multidatabase2;

/**
 * @author leexm
 * @date 2020/7/8 15:49
 */
public class DbContextHolder {

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "master";
        }
    };

    public static String getDb() {
        return HOLDER.get();
    }

    public static void setDb(String db) {
        HOLDER.set(db);
    }

    public static void clearDb() {
        HOLDER.remove();
    }

}
