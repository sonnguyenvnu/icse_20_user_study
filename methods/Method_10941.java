/** 
 * ??????????
 * @param strDate ???????
 * @return dayForWeek ????
 * @Exception ????<br>
 */
public static int stringForWeek(String strDate,SimpleDateFormat simpleDateFormat) throws Exception {
  Calendar c=Calendar.getInstance();
  c.setTime(simpleDateFormat.parse(strDate));
  if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
    return 7;
  }
 else {
    return c.get(Calendar.DAY_OF_WEEK) - 1;
  }
}
