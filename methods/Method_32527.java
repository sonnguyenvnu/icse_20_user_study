/** 
 * Constructs a MonthDay from a <code>java.util.Date</code> using exactly the same field values avoiding any time zone effects. <p> Each field is queried from the Date and assigned to the MonthDay. <p> This factory method always creates a MonthDay with ISO chronology.
 * @param date  the Date to extract fields from
 * @return the created MonthDay, never null
 * @throws IllegalArgumentException if the calendar is null
 * @throws IllegalArgumentException if the monthOfYear or dayOfMonth is invalid for the ISO chronology
 */
@SuppressWarnings("deprecation") public static MonthDay fromDateFields(Date date){
  if (date == null) {
    throw new IllegalArgumentException("The date must not be null");
  }
  return new MonthDay(date.getMonth() + 1,date.getDate());
}
