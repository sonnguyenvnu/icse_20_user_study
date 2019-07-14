/** 
 * Returns total number of transactions associated with current thread.
 */
public int totalThreadTransactions(){
  ArrayList<JtxTransaction> txList=txStack.get();
  if (txList == null) {
    return 0;
  }
  return txList.size();
}
