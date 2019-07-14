/** 
 * Get this object as a <code>MutablePeriod</code>. <p> This will always return a new <code>MutablePeriod</code> with the same fields.
 * @return a MutablePeriod using the same field set and values
 */
public MutablePeriod toMutablePeriod(){
  return new MutablePeriod(this);
}
