/** 
 * Determine if this day is within a specified range
 * @param minDate the earliest day, may be null
 * @param maxDate the latest day, may be null
 * @return true if the between (inclusive) the min and max dates.
 */
public boolean isInRange(@Nullable CalendarDay minDate,@Nullable CalendarDay maxDate){
  return !(minDate != null && minDate.isAfter(this)) && !(maxDate != null && maxDate.isBefore(this));
}
