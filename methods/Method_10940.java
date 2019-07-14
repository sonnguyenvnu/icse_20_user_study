/** 
 * ??????????
 * @param strDate ???????
 * @return dayForWeek ????
 * @Exception ????<br>
 */
public static int stringForWeek(String strDate) throws Exception {
  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
  Calendar c=Calendar.getInstance();
  c.setTime(format.parse(strDate));
  if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
    return 7;
  }
 else {
    return c.get(Calendar.DAY_OF_WEEK) - 1;
  }
}
