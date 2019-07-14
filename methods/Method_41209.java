/** 
 * <p> Determine whether the given time (in milliseconds) is 'included' by the Calendar. </p> <p> Note that this Calendar is only has full-day precision. </p>
 */
@Override public boolean isTimeIncluded(long timeStamp){
  if (excludeAll == true) {
    return false;
  }
  if (super.isTimeIncluded(timeStamp) == false) {
    return false;
  }
  java.util.Calendar cl=createJavaCalendar(timeStamp);
  int wday=cl.get(java.util.Calendar.DAY_OF_WEEK);
  return !(isDayExcluded(wday));
}
