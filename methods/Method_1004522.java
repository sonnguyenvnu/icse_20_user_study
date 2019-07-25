/** 
 * Await at most <code>timeout</code> before throwing a timeout exception.
 * @param timeout the timeout
 * @param unit    the unit
 * @return the condition factory
 */
public ConditionFactory timeout(long timeout,TimeUnit unit){
  return atMost(timeout,unit);
}
