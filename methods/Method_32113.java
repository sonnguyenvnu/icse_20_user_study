/** 
 * Converts this object to a <code>TimeOfDay</code> using the same millis and chronology.
 * @return a TimeOfDay using the same millis and chronology
 * @deprecated Use LocalTime instead of TimeOfDay
 */
@Deprecated public TimeOfDay toTimeOfDay(){
  return new TimeOfDay(getMillis(),getChronology());
}
