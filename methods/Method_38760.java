/** 
 * Check if propagation of a transaction is possible, due to source and destination transaction modes.
 * @see #setValidateExistingTransaction(boolean) 
 */
protected void continueTx(final JtxTransaction sourceTx,final JtxTransactionMode destMode){
  if (!validateExistingTransaction) {
    return;
  }
  JtxTransactionMode sourceMode=sourceTx.getTransactionMode();
  JtxIsolationLevel destIsolationLevel=destMode.getIsolationLevel();
  if (destIsolationLevel != ISOLATION_DEFAULT) {
    JtxIsolationLevel currentIsolationLevel=sourceMode.getIsolationLevel();
    if (currentIsolationLevel != destIsolationLevel) {
      throw new JtxException("Participating TX specifies isolation level: " + destIsolationLevel + " which is incompatible with existing TX: " + currentIsolationLevel);
    }
  }
  if ((!destMode.isReadOnly()) && (sourceMode.isReadOnly())) {
    throw new JtxException("Participating TX is not marked as read-only, but existing TX is");
  }
}
