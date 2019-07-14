/** 
 * Gets the millis, which is the ISO parsed string value.
 * @param object  the String to convert, must not be null
 * @param chrono  the chronology to use, non-null result of getChronology
 * @return the millisecond value
 * @throws IllegalArgumentException if the value if invalid
 */
public long getInstantMillis(Object object,Chronology chrono){
  String str=(String)object;
  DateTimeFormatter p=ISODateTimeFormat.dateTimeParser();
  return p.withChronology(chrono).parseMillis(str);
}
