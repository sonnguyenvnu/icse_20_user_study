/** 
 * Converts the duration of the interval to a <code>Period</code> using the specified period type. <p> This method should be used to extract the field values describing the difference between the start and end instants.
 * @param type  the requested type of the duration, null means AllType
 * @return a time period derived from the interval
 */
public Period toPeriod(PeriodType type){
  return new Period(getStartMillis(),getEndMillis(),type,getChronology());
}
