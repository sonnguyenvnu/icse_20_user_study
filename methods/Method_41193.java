/** 
 * Determines whether the given time (in milliseconds) is 'included' by the <CODE>BaseCalendar</CODE>
 * @param timeInMillis the date/time to test
 * @return a boolean indicating whether the specified time is 'included' bythe <CODE>CronCalendar</CODE>
 */
@Override public boolean isTimeIncluded(long timeInMillis){
  if ((getBaseCalendar() != null) && (getBaseCalendar().isTimeIncluded(timeInMillis) == false)) {
    return false;
  }
  return (!(cronExpression.isSatisfiedBy(new Date(timeInMillis))));
}
