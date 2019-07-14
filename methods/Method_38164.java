/** 
 * Starts a transaction.
 */
public void beginTransaction(final DbTransactionMode mode){
  log.debug("Beginning transaction");
  assertTxIsClosed();
  this.txMode=mode;
  openTx();
}
