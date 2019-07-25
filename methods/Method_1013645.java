/** 
 * Queries whether the given ConditionObject uses this synchronizer as its lock.
 * @param condition the condition
 * @return <tt>true</tt> if owned
 * @throws NullPointerException if the condition is null
 */
public final boolean owns(ConditionObject condition){
  if (condition == null)   throw new NullPointerException();
  return condition.isOwnedBy(this);
}
