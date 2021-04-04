import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * @ClassName: test
 * @Description: java类作用描述
 * @Author: 任宏腾
 * @CreateDate: 2020/11/24$ 17:29$
 * @Version: 1.0
 */

public class test {
    public static void main(String[] args) throws ParseException {
        String a = getWeekOfMonday("2020-11-25");
        String b = getMonOfMonday("2020-11-25");
        String c = getQuarter("2020-11-25");
        String d = getYear("2020-11-25");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    public static String getWeekOfMonday(String date) throws ParseException {
        Date crtDay = DateUtils.parseDate(date,new String[]{"yyyy-MM-dd"});
        Calendar clDay = Calendar.getInstance();
        clDay.setTime(crtDay);
        Iterator<Calendar> it = DateUtils.iterator(clDay, 2);
        Calendar monday = it.next();
        return DateFormatUtils.format(monday, "yyyy-MM-dd");
    }

    public static String getMonOfMonday(String date) throws ParseException {
        Date crtDay = DateUtils.parseDate(date,new String[]{"yyyy-MM-dd"});
        Calendar clDay = Calendar.getInstance();
        clDay.setTime(crtDay);
        Calendar round = DateUtils.round(clDay, 2);
        return DateFormatUtils.format(round, "yyyy-MM-dd");
    }

    public static String getQuarter(String date) throws ParseException {
        Date crtDay = DateUtils.parseDate(date,new String[]{"yyyy-MM-dd"});
        Calendar clDay = Calendar.getInstance();
        clDay.setTime(crtDay);
        int month = clDay.get(2);
        int quarter = month / 3;
        clDay.set(2, quarter * 3);
        Calendar round = DateUtils.round(clDay, 2);
        return DateFormatUtils.format(round, "yyyy-MM-dd");
    }

    public static String getYear(String date) throws ParseException {
        Date crtDay = DateUtils.parseDate(date,new String[]{"yyyy-MM-dd"});
        Calendar clDay = Calendar.getInstance();
        clDay.setTime(crtDay);
        Calendar truncate = DateUtils.truncate(clDay, 1);
        return DateFormatUtils.format(truncate, "yyyy-MM-dd");
    }

}
