/** 
 * Get this interval as an immutable <code>Interval</code> object.
 * @return the interval as an Interval object
 */
public Interval toInterval(){
  return new Interval(getStartMillis(),getEndMillis(),getChronology());
}
