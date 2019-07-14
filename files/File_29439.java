package com.sohu.cache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 调度相关的工具类
 * <p/>
 * Created by yijunzhang on 14-6-10.
 */
public class ScheduleUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleUtil.class);

    private static final String COLLECT_TIME_FORMAT = "yyyyMMddHHmm";
    private static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * cron表达�?：�?分钟，根�?�appId计算分钟的秒数
     *
     * @param appId
     * @return
     */
    public static String getMinuteCronByAppId(long appId) {
        String baseCron = (appId % 50) + " 0/1 * ? * *";
        return baseCron;
    }
    
    public static String getMachineStatsCron(long hostId) {
        String baseCron = (hostId % 50) + " 0/" + ConstUtils.MACHINE_STATS_CRON_MINUTE + " * ? * *";
        return baseCron;
    }
    
    public static String getFiveMinuteCronByHostId(long hostId) {
        String baseCron = (hostId % 50) + " 0/5 * ? * *";
        return baseCron;
    }
    
    public static String getRedisSlowLogCron(long appId) {
        Random random = new Random();
        String baseCron = random.nextInt(60) + " 0/20 * ? * *";
        return baseCron;
    }

    /**
     * cron表达�?：�?�?时，根�?�hostId计算�?时的分钟数
     *
     * @param hostId
     * @return
     */
    public static String getHourCronByHostId(long hostId) {
        String hourCron = "0 %s 0/1 ? * *";
        Random random = new Random();
        long minute = (hostId + random.nextInt(Integer.MAX_VALUE)) % 60;
        return String.format(hourCron, minute);
    }

    /**
     * 计算�?一分钟的时间，并格�?化
     *
     * @param collectTime 基准时间
     * @return
     */
    public static long getLastCollectTime(long collectTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(COLLECT_TIME_FORMAT);
        try {
            Date date = simpleDateFormat.parse(String.valueOf(collectTime));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, -1);
            Date lastDate = calendar.getTime();
            return Long.valueOf(simpleDateFormat.format(lastDate));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 格�?化时间
     *
     * @param date
     * @return
     */
    public static long getCollectTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(COLLECT_TIME_FORMAT);
        return Long.valueOf(simpleDateFormat.format(date));
    }

    /**
     * 返回�?一天的起始时间，如：201406300000
     *
     * @param date  当�?日期
     * @param offset 针对当�?日期的�??移
     * @return 日期的long形�?，如201406300000
     */
    public static long getBeginTimeOfDay(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return Long.valueOf(dateFormat.format(date) + "0000");
    }
}
