package com.java.util;

import java.util.Date;

public class DaysUtil {
    //计算日期
    public static Long Calculatedays(Date before, Date after, Long price) {
        long nd = 1000 * 24 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = after.getTime() - before.getTime();
        // 计算差多少天
        long day = diff / nd;
        Long total = (day + 1) * price;
        return total;
    }
}
