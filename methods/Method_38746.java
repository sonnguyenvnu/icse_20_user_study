/** 
 * Returns <code>true</code> if transaction is marked as rollback only.
 */
public boolean isRollbackOnly(){
  return status == STATUS_MARKED_ROLLBACK;
}
