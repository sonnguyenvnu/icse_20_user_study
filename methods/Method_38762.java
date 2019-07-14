/** 
 * Propagation: REQUIRES_NEW <pre> {@code None -> T2 T1   -> T2}</pre>
 */
@SuppressWarnings({"UnusedDeclaration"}) protected JtxTransaction propRequiresNew(final JtxTransaction currentTx,final JtxTransactionMode mode,final Object scope){
  return createNewTransaction(mode,scope,true);
}
