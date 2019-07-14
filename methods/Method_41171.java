/** 
 * <p> Returns a date that is rounded to the next even multiple of the given minute. </p> <p> The rules for calculating the second are the same as those for calculating the minute in the method  <code>getNextGivenMinuteDate(..)<code>. </p>
 * @param date the Date to round, if <code>null</code> the current time willbe used
 * @param secondBase the base-second to set the time on
 * @return the new rounded date
 * @see #nextGivenMinuteDate(Date,int)
 */
public static Date nextGivenSecondDate(Date date,int secondBase){
  if (secondBase < 0 || secondBase > 59) {
    throw new IllegalArgumentException("secondBase must be >=0 and <= 59");
  }
  if (date == null) {
    date=new Date();
  }
  Calendar c=Calendar.getInstance();
  c.setTime(date);
  c.setLenient(true);
  if (secondBase == 0) {
    c.set(Calendar.MINUTE,c.get(Calendar.MINUTE) + 1);
    c.set(Calendar.SECOND,0);
    c.set(Calendar.MILLISECOND,0);
    return c.getTime();
  }
  int second=c.get(Calendar.SECOND);
  int arItr=second / secondBase;
  int nextSecondOccurance=secondBase * (arItr + 1);
  if (nextSecondOccurance < 60) {
    c.set(Calendar.SECOND,nextSecondOccurance);
    c.set(Calendar.MILLISECOND,0);
    return c.getTime();
  }
 else {
    c.set(Calendar.MINUTE,c.get(Calendar.MINUTE) + 1);
    c.set(Calendar.SECOND,0);
    c.set(Calendar.MILLISECOND,0);
    return c.getTime();
  }
}
