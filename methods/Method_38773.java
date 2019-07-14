/** 
 * Requests for transaction and returns non-null value <b>only</b> when new transaction is created! When <code>null</code> is returned, transaction may be get by {@link #getCurrentTransaction()}.
 * @see jodd.jtx.JtxTransactionManager#requestTransaction(jodd.jtx.JtxTransactionMode)
 */
public JtxTransaction maybeRequestTransaction(final JtxTransactionMode txMode,final Object scope){
  if (txMode == null) {
    return null;
  }
  JtxTransaction currentTx=txManager.getTransaction();
  JtxTransaction requestedTx=txManager.requestTransaction(txMode,scope);
  if (currentTx == requestedTx) {
    return null;
  }
  return requestedTx;
}
