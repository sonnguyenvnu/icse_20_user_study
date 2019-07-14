/** 
 * Get this period as an immutable <code>Period</code> object. The period will use <code>PeriodType.standard()</code>.
 * @return a <code>Period</code> representing the same number of days
 */
public Period toPeriod(){
  return Period.ZERO.withFields(this);
}
