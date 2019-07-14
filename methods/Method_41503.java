/** 
 * <p> Determines whether the date and (optionally) time of the given Calendar  instance falls on a scheduled fire-time of this trigger. </p> <p> Note that the value returned is NOT validated against the related org.quartz.Calendar (if any) </p>
 * @param test the date to compare
 * @param dayOnly if set to true, the method will only determine if thetrigger will fire during the day represented by the given Calendar (hours, minutes and seconds will be ignored).
 * @see #willFireOn(Calendar)
 */
public boolean willFireOn(Calendar test,boolean dayOnly){
  test=(Calendar)test.clone();
  test.set(Calendar.MILLISECOND,0);
  if (dayOnly) {
    test.set(Calendar.HOUR_OF_DAY,0);
    test.set(Calendar.MINUTE,0);
    test.set(Calendar.SECOND,0);
  }
  Date testTime=test.getTime();
  Date fta=getFireTimeAfter(new Date(test.getTime().getTime() - 1000));
  if (fta == null)   return false;
  Calendar p=Calendar.getInstance(test.getTimeZone());
  p.setTime(fta);
  int year=p.get(Calendar.YEAR);
  int month=p.get(Calendar.MONTH);
  int day=p.get(Calendar.DATE);
  if (dayOnly) {
    return (year == test.get(Calendar.YEAR) && month == test.get(Calendar.MONTH) && day == test.get(Calendar.DATE));
  }
  while (fta.before(testTime)) {
    fta=getFireTimeAfter(fta);
  }
  return fta.equals(testTime);
}
