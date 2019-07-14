/** 
 * <p> Get a <code>Date</code> object that represents the given time, on the given date. </p>
 * @param second The value (0-59) to give the seconds field of the date
 * @param minute The value (0-59) to give the minutes field of the date
 * @param hour The value (0-23) to give the hours field of the date
 * @param dayOfMonth The value (1-31) to give the day of month field of the date
 * @param month The value (1-12) to give the month field of the date
 * @param year The value (1970-2099) to give the year field of the date
 * @return the new date
 */
public static Date dateOf(int hour,int minute,int second,int dayOfMonth,int month,int year){
  validateSecond(second);
  validateMinute(minute);
  validateHour(hour);
  validateDayOfMonth(dayOfMonth);
  validateMonth(month);
  validateYear(year);
  Date date=new Date();
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  c.set(Calendar.YEAR,year);
  c.set(Calendar.MONTH,month - 1);
  c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
  c.set(Calendar.HOUR_OF_DAY,hour);
  c.set(Calendar.MINUTE,minute);
  c.set(Calendar.SECOND,second);
  c.set(Calendar.MILLISECOND,0);
  return c.getTime();
}
