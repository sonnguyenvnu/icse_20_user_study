/** 
 * Converts this duration to a Period instance by adding the duration to a start instant to obtain an interval using the standard period type. <p> This conversion will determine the fields of a period accurately. The results are based on the instant millis, the chronology of the instant, the standard period type and the length of this duration.
 * @param startInstant  the instant to calculate the period from, null means now
 * @return a Period created using the millisecond duration from this instance
 */
public Period toPeriodFrom(ReadableInstant startInstant){
  return new Period(startInstant,this);
}
