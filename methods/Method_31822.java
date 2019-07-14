/** 
 * Converts the duration of the interval to a <code>Period</code> using the All period type. <p> This method should be used to extract the field values describing the difference between the start and end instants.
 * @return a time period derived from the interval
 */
public Period toPeriod(){
  return new Period(getStartMillis(),getEndMillis(),getChronology());
}
