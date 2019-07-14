/** 
 * <p> Check if all days are excluded. That is no day is included. </p>
 */
public boolean areAllDaysExcluded(){
  for (int i=1; i <= MAX_DAYS_IN_MONTH; i++) {
    if (isDayExcluded(i) == false) {
      return false;
    }
  }
  return true;
}
