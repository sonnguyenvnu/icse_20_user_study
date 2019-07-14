/** 
 * Converts this duration to a Period instance by adding the duration to a start instant to obtain an interval. <p> This conversion will determine the fields of a period accurately. The results are based on the instant millis, the chronology of the instant, the period type and the length of this duration.
 * @param startInstant  the instant to calculate the period from, null means now
 * @param type  the period type determining how to split the duration into fields, null means All type
 * @return a Period created using the millisecond duration from this instance
 */
public Period toPeriodFrom(ReadableInstant startInstant,PeriodType type){
  return new Period(startInstant,this,type);
}
