/** 
 * Returns span between two days. Returned value may be positive (when this date is after the provided one) or negative (when comparing to future date).
 */
public int daysSpan(final JulianDate otherDate){
  int now=getJulianDayNumber();
  int then=otherDate.getJulianDayNumber();
  return now - then;
}
