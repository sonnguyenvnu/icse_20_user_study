/** 
 * Get this period as an immutable <code>Period</code> object.
 * @return a Period using the same field set and values
 */
public Period toPeriod(){
  return new Period(this);
}
