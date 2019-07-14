/** 
 * Propagation: NOT_SUPPORTED <pre> {@code None -> None T1   -> None}</pre>
 */
protected JtxTransaction propNotSupported(final JtxTransaction currentTx,final JtxTransactionMode mode,final Object scope){
  if (currentTx == null) {
    return createNewTransaction(mode,scope,false);
  }
  if (currentTx.isNoTransaction()) {
    return currentTx;
  }
  return createNewTransaction(mode,scope,false);
}
