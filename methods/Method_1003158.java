/** 
 * Begin a new transaction.
 * @param listener to be notified in case of a rollback
 * @param timeoutMillis to wait for a blocking transaction
 * @param ownerId of the owner (Session?) to be reported by getBlockerId
 * @return the transaction
 */
public Transaction begin(RollbackListener listener,int timeoutMillis,int ownerId){
  if (timeoutMillis <= 0) {
    timeoutMillis=this.timeoutMillis;
  }
  Transaction transaction=registerTransaction(0,Transaction.STATUS_OPEN,null,0,timeoutMillis,ownerId,listener);
  return transaction;
}
