/** 
 * ????????
 * @param birthday
 * @return constellation
 */
public static String getStar(Date birthday){
  Calendar c=Calendar.getInstance();
  c.setTime(birthday);
  int month=c.get(Calendar.MONTH);
  int dayOfMonth=c.get(Calendar.DAY_OF_MONTH);
  int[] DayArr={19,18,20,19,20,21,22,22,22,23,22,21};
  String[] starArr={"???","???","???","???","???","???","???","???","???","???","???","???"};
  if (dayOfMonth > DayArr[month]) {
    month=month + 1;
    if (month == 12) {
      month=0;
    }
  }
  return starArr[month];
}
