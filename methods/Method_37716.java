/** 
 * Calculates the number of days between two dates. Returned value is always positive.
 */
public int daysBetween(final JulianDate otherDate){
  int difference=daysSpan(otherDate);
  return difference >= 0 ? difference : -difference;
}
