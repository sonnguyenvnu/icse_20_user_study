package com.sohu.cache.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leifu
 * @Time 2014å¹´8æœˆ31æ—¥
 */
public class DateUtil {
    private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    
    /*
     * yyyyMMddHHmmæ ¼å¼?format
     */
    public static String formatYYYYMMddHHMM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.format(date);
    }

    /*
     * yyyy-MM-dd HH:mm:ssæ ¼å¼?format
     */
    public static String formatYYYYMMddHHMMSS(Date date) {
        if(date == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /*
     * yyyyMMddHHmmæ ¼å¼?parse
     */
    public static Date parse(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }
    
    /*
     * yyyyMMddHHmmæ ¼å¼?parse
     */
    public static Date parseYYYYMMddHHMM(String dateStr) throws ParseException {
        return parse(dateStr, "yyyyMMddHHmm");
    }

    /**
     * yyyyMMddHHæ ¼å¼?parse
     *
     * @throws ParseException
     */
    public static Date parseYYYYMMddHH(String dateStr) throws ParseException {
        return parse(dateStr, "yyyyMMddHH");
    }


    /*
     * yyyy-MM-ddæ ¼å¼?parse
     */
    public static Date parseYYYY_MM_dd(String dateStr) throws ParseException {
        return parse(dateStr, "yyyy-MM-dd");
    }

    /**
     * yyyyMMddæ ¼å¼?parse
     */
    public static Date parseYYYYMMdd(String dateStr) throws ParseException {
        return parse(dateStr, "yyyyMMdd");
    }


    public static Date getDateByFormat(String date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date result = null;
        try {
            result = sf.parse(date);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }


    public static String formatYYYYMMdd(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(date);
    }

    public static String formatHHMM(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("HHmm");
        return sf.format(date);
    }
}
