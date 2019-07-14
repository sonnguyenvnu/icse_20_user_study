/** 
 * <p> Returns a date that is rounded to the next even second above the given date. </p>
 * @param date the Date to round, if <code>null</code> the current time will be used
 * @return the new rounded date
 */
public static Date evenSecondDate(Date date){
  if (date == null) {
    date=new Date();
  }
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  c.setLenient(true);
  c.set(Calendar.SECOND,c.get(Calendar.SECOND) + 1);
  c.set(Calendar.MILLISECOND,0);
  return c.getTime();
}
