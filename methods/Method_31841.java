/** 
 * Converts this duration to a Period instance using the standard period type and the specified chronology. <p> Only precise fields in the period type will be used. Exactly which fields are precise depends on the chronology. Only the time fields are precise for ISO chronology with a time zone. However, ISO UTC also has precise days and weeks. <p> For more control over the conversion process, you must pair the duration with an instant, see  {@link #toPeriodFrom(ReadableInstant)} and{@link #toPeriodTo(ReadableInstant)}
 * @param chrono  the chronology to use, null means ISO default
 * @return a Period created using the millisecond duration from this instance
 */
public Period toPeriod(Chronology chrono){
  return new Period(getMillis(),chrono);
}
