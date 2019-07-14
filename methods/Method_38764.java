/** 
 * Propagation: MANDATORY <pre> {@code None -> Error T1   -> T1 (cont.)}</pre>
 */
@SuppressWarnings({"UnusedDeclaration"}) protected JtxTransaction propMandatory(final JtxTransaction currentTx,final JtxTransactionMode mode,final Object scope){
  if ((currentTx == null) || (currentTx.isNoTransaction())) {
    throw new JtxException("No existing TX found for TX marked with propagation 'mandatory'");
  }
  continueTx(currentTx,mode);
  return currentTx;
}
