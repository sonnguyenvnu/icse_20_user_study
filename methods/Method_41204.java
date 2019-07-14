/** 
 * <p> Determine the next time (in milliseconds) that is 'included' by the Calendar after the given time. Return the original value if timeStamp is included. Return 0 if all days are excluded. </p> <p> Note that this Calendar is only has full-day precision. </p>
 */
@Override public long getNextIncludedTime(long timeStamp){
  if (excludeAll == true) {
    return 0;
  }
  long baseTime=super.getNextIncludedTime(timeStamp);
  if ((baseTime > 0) && (baseTime > timeStamp)) {
    timeStamp=baseTime;
  }
  java.util.Calendar cl=getStartOfDayJavaCalendar(timeStamp);
  int day=cl.get(java.util.Calendar.DAY_OF_MONTH);
  if (!isDayExcluded(day)) {
    return timeStamp;
  }
  while (isDayExcluded(day) == true) {
    cl.add(java.util.Calendar.DATE,1);
    day=cl.get(java.util.Calendar.DAY_OF_MONTH);
  }
  return cl.getTime().getTime();
}
