/** 
 * Converts this object to a LocalDate with the same year-month and chronology.
 * @param dayOfMonth the day of month to use, valid for chronology, such as 1-31 for ISO
 * @return a LocalDate with the same year-month and chronology, never null
 */
public LocalDate toLocalDate(int dayOfMonth){
  return new LocalDate(getYear(),getMonthOfYear(),dayOfMonth,getChronology());
}
