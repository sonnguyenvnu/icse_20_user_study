/** 
 * Returns a copy of this date with a different chronology, potentially changing the day in unexpected ways. <p> This method creates a new DateMidnight using the midnight millisecond value and the new chronology. If the same or similar chronology is specified, but with a different time zone, the day may change. This occurs because the new DateMidnight rounds down the millisecond value to get to midnight, and the time zone change may result in a rounding down to a different day. <p> For example, changing time zone from London (+00:00) to Paris (+01:00) will retain the same day, but changing from Paris to London will change the day. (When its midnight in London its the same day in Paris, but when its midnight in Paris its still the previous day in London) <p> To avoid these unusual effects, use  {@link #withZoneRetainFields(DateTimeZone)}to change time zones.
 * @param newChronology  the new chronology
 * @return a copy of this instant with a different chronology
 */
public DateMidnight withChronology(Chronology newChronology){
  return (newChronology == getChronology() ? this : new DateMidnight(getMillis(),newChronology));
}
