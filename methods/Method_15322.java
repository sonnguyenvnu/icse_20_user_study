/** 
 * ??????
 * @param birthday
 * @param needYear
 * @return
 */
public static String getSmartBirthday(long birthday,boolean needYear){
  int[] birthdayDetails=getDateDetail(birthday);
  int[] nowDetails=getDateDetail(System.currentTimeMillis());
  Calendar birthdayCalendar=Calendar.getInstance();
  birthdayCalendar.set(birthdayDetails[0],birthdayDetails[1],birthdayDetails[2]);
  Calendar nowCalendar=Calendar.getInstance();
  nowCalendar.set(nowDetails[0],nowDetails[1],nowDetails[2]);
  int days=birthdayCalendar.get(Calendar.DAY_OF_YEAR) - nowCalendar.get(Calendar.DAY_OF_YEAR);
  if (days < 8) {
    if (days >= 3) {
      return days + "??";
    }
    if (days >= 2) {
      return TimeUtil.Day.NAME_THE_DAY_AFTER_TOMORROW;
    }
    if (days >= 1) {
      return TimeUtil.Day.NAME_TOMORROW;
    }
    if (days >= 0) {
      return TimeUtil.Day.NAME_TODAY;
    }
  }
  if (needYear) {
    return birthdayDetails[0] + "?" + birthdayDetails[1] + "?" + birthdayDetails[2] + "?";
  }
  return birthdayDetails[1] + "?" + birthdayDetails[2] + "?";
}
