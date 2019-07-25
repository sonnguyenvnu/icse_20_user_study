/** 
 * Await at most <code>timeout</code> before throwing a timeout exception.
 * @param timeout the timeout
 * @return the condition factory
 */
public ConditionFactory timeout(Duration timeout){
  return atMost(timeout);
}
