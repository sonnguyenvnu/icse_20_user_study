/** 
 * Converts this duration to a Period instance using the specified period type and the ISO chronology. <p> Only precise fields in the period type will be used. At most these are hours, minutes, seconds and millis - the period type may restrict the selection further. <p> For more control over the conversion process, you must pair the duration with an instant, see  {@link #toPeriodFrom(ReadableInstant,PeriodType)}.
 * @param type  the period type to use, null means standard
 * @return a Period created using the millisecond duration from this instance
 */
public Period toPeriod(PeriodType type){
  return new Period(getMillis(),type);
}
