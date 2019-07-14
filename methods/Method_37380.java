/** 
 * Returns <code>true</code> if prune of expired objects should be invoked. For internal use.
 */
protected boolean isPruneExpiredActive(){
  return (timeout != 0) || existCustomTimeout;
}
