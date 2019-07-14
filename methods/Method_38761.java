/** 
 * Propagation: REQUIRED <pre> {@code None -> T2 T1   -> T1 (cont.)}</pre>
 */
protected JtxTransaction propRequired(JtxTransaction currentTx,final JtxTransactionMode mode,final Object scope){
  if ((currentTx == null) || (currentTx.isNoTransaction())) {
    currentTx=createNewTransaction(mode,scope,true);
  }
 else {
    continueTx(currentTx,mode);
  }
  return currentTx;
}
