/** 
 * Returns true, if the given calendar matches into the range.
 */
public boolean matches(Calendar cal){
  if (start == null && end == null) {
    return false;
  }
  long matchStart=start != null ? start.getTimeInMillis() : DateTimeUtils.truncateToMidnight(cal).getTimeInMillis();
  long matchEnd=end != null ? end.getTimeInMillis() : DateTimeUtils.endOfDayDate(cal).getTimeInMillis();
  return cal.getTimeInMillis() >= matchStart && cal.getTimeInMillis() < matchEnd;
}
