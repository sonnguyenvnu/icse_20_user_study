/** 
 * Get this object as a <code>MutablePeriod</code>. <p> This will always return a new <code>MutablePeriod</code> with the same fields. The period will use <code>PeriodType.standard()</code>.
 * @return a MutablePeriod using the same field set and values
 */
public MutablePeriod toMutablePeriod(){
  MutablePeriod period=new MutablePeriod();
  period.add(this);
  return period;
}
