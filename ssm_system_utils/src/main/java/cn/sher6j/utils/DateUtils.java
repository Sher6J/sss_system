package cn.sher6j.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sher6j
 * @create 2020-04-18-16:02
 */
public class DateUtils {

    /**
     * 日期转换成字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String data2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String str, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = sdf.parse(str);
        return parse;
    }
}
