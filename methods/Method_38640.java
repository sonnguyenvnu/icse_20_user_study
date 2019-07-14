/** 
 * Starts new read/write transaction in PROPAGATION_REQUIRED mode.
 */
private JtxTransaction startRwTx(){
  if (jtxManager == null) {
    return null;
  }
  return jtxManager.requestTransaction(new JtxTransactionMode(JtxPropagationBehavior.PROPAGATION_REQUIRED,false));
}
