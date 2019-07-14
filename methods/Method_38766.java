/** 
 * Propagation: NEVER <pre> {@code None -> None T1   -> Error}</pre>
 */
protected JtxTransaction propNever(JtxTransaction currentTx,final JtxTransactionMode mode,final Object scope){
  if ((currentTx != null) && (!currentTx.isNoTransaction())) {
    throw new JtxException("Existing TX found for TX marked with propagation 'never'");
  }
  if (currentTx == null) {
    currentTx=createNewTransaction(mode,scope,false);
  }
  return currentTx;
}
