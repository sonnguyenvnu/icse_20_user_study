/** 
 * Returns total number of transactions of the specified status associated with current thread.
 */
public int totalThreadTransactionsWithStatus(final JtxStatus status){
  ArrayList<JtxTransaction> txlist=txStack.get();
  if (txlist == null) {
    return 0;
  }
  int count=0;
  for (  JtxTransaction tx : txlist) {
    if (tx.getStatus() == status) {
      count++;
    }
  }
  return count;
}
