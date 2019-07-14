/** 
 * Converts this object to a <code>YearMonthDay</code> using the same millis and chronology.
 * @return a YearMonthDay using the same millis and chronology
 * @deprecated Use LocalDate instead of YearMonthDay
 */
@Deprecated public YearMonthDay toYearMonthDay(){
  return new YearMonthDay(getMillis(),getChronology());
}
