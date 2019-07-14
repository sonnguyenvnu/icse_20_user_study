/** 
 * Returns a copy of this datetime with the specified date, retaining the time fields. <p> If the time is invalid on the new date due to the time-zone, the time will be adjusted. <p> This instance is immutable and unaffected by this method call.
 * @param date  the local date
 * @return a copy of this datetime with a different date
 * @throws IllegalArgumentException if the time-of-day is invalid for the date
 * @throws NullPointerException if the date is null
 */
public DateTime withDate(LocalDate date){
  return withDate(date.getYear(),date.getMonthOfYear(),date.getDayOfMonth());
}
