/** 
 * <p> Returns a date that is rounded to the previous even hour below the given date. </p> <p> For example an input date with a time of 08:13:54 would result in a date with the time of 08:00:00. </p>
 * @param date the Date to round, if <code>null</code> the current time will be used
 * @return the new rounded date
 */
public static Date evenHourDateBefore(Date date){
  if (date == null) {
    date=new Date();
  }
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  c.set(Calendar.MINUTE,0);
  c.set(Calendar.SECOND,0);
  c.set(Calendar.MILLISECOND,0);
  return c.getTime();
}
