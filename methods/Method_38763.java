/** 
 * Propagation: SUPPORTS <pre> {@code None -> None T1   -> T1 (cont.)}</pre>
 */
protected JtxTransaction propSupports(JtxTransaction currentTx,final JtxTransactionMode mode,final Object scope){
  if ((currentTx != null) && (!currentTx.isNoTransaction())) {
    continueTx(currentTx,mode);
  }
  if (currentTx == null) {
    currentTx=createNewTransaction(mode,scope,false);
  }
  return currentTx;
}
