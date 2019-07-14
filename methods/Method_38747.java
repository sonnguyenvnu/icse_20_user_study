/** 
 * Sets the rollback-only if the deadline has been reached and throws an exception.
 */
protected void checkTimeout(){
  if (deadline == DEFAULT_TIMEOUT) {
    return;
  }
  if (this.deadline - System.currentTimeMillis() < 0) {
    setRollbackOnly();
    throw new JtxException("TX timed out, marked as rollback only");
  }
}
