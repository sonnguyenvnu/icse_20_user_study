/** 
 * <p> Check if all week days are excluded. That is no day is included. </p>
 * @return boolean
 */
public boolean areAllDaysExcluded(){
  return isDayExcluded(java.util.Calendar.SUNDAY) && isDayExcluded(java.util.Calendar.MONDAY) && isDayExcluded(java.util.Calendar.TUESDAY) && isDayExcluded(java.util.Calendar.WEDNESDAY) && isDayExcluded(java.util.Calendar.THURSDAY) && isDayExcluded(java.util.Calendar.FRIDAY) && isDayExcluded(java.util.Calendar.SATURDAY);
}
