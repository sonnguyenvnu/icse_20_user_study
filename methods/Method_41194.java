/** 
 * Determines whether the given time (in milliseconds) is 'included' by the <CODE>BaseCalendar</CODE>
 * @param timeInMillis the date/time to test
 * @return a boolean indicating whether the specified time is 'included' bythe <CODE>BaseCalendar</CODE>
 */
@Override public boolean isTimeIncluded(long timeInMillis){
  if ((getBaseCalendar() != null) && (getBaseCalendar().isTimeIncluded(timeInMillis) == false)) {
    return false;
  }
  long startOfDayInMillis=getStartOfDayJavaCalendar(timeInMillis).getTime().getTime();
  long endOfDayInMillis=getEndOfDayJavaCalendar(timeInMillis).getTime().getTime();
  long timeRangeStartingTimeInMillis=getTimeRangeStartingTimeInMillis(timeInMillis);
  long timeRangeEndingTimeInMillis=getTimeRangeEndingTimeInMillis(timeInMillis);
  if (!invertTimeRange) {
    return ((timeInMillis > startOfDayInMillis && timeInMillis < timeRangeStartingTimeInMillis) || (timeInMillis > timeRangeEndingTimeInMillis && timeInMillis < endOfDayInMillis));
  }
 else {
    return ((timeInMillis >= timeRangeStartingTimeInMillis) && (timeInMillis <= timeRangeEndingTimeInMillis));
  }
}
