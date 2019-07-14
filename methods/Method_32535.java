/** 
 * Converts this object to a LocalDate with the same month-day and chronology.
 * @param year  the year to use, valid for chronology
 * @return a LocalDate with the same month-day and chronology, never null
 */
public LocalDate toLocalDate(int year){
  return new LocalDate(year,getMonthOfYear(),getDayOfMonth(),getChronology());
}
