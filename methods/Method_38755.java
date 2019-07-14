/** 
 * Returns total number of active transactions associated with current thread.
 */
public int totalActiveThreadTransactions(){
  return totalThreadTransactionsWithStatus(STATUS_ACTIVE);
}
