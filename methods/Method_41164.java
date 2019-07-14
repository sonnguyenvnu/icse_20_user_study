/** 
 * <p> Returns a date that is rounded to the next even hour above the given date. </p> <p> For example an input date with a time of 08:13:54 would result in a date with the time of 09:00:00. If the date's time is in the 23rd hour, the date's 'day' will be promoted, and the time will be set to 00:00:00. </p>
 * @param date the Date to round, if <code>null</code> the current time will be used
 * @return the new rounded date
 */
public static Date evenHourDate(Date date){
  if (date == null) {
    date=new Date();
  }
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  c.setLenient(true);
  c.set(Calendar.HOUR_OF_DAY,c.get(Calendar.HOUR_OF_DAY) + 1);
  c.set(Calendar.MINUTE,0);
  c.set(Calendar.SECOND,0);
  c.set(Calendar.MILLISECOND,0);
  return c.getTime();
}
