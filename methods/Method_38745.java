/** 
 * Modify the transaction associated with the target object such that the only possible outcome of the transaction is to roll back the transaction.
 */
public void setRollbackOnly(final Throwable th){
  if (!isNoTransaction()) {
    if ((status != STATUS_MARKED_ROLLBACK) && (status != STATUS_ACTIVE)) {
      throw new JtxException("TNo active TX that can be marked as rollback only");
    }
  }
  rollbackCause=th;
  status=STATUS_MARKED_ROLLBACK;
}
