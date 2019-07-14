/** 
 * Associate transaction to current thread.
 */
protected void associateTransaction(final JtxTransaction tx){
  totalTransactions++;
  ArrayList<JtxTransaction> txList=txStack.get();
  if (txList == null) {
    txList=new ArrayList<>();
    txStack.set(txList);
  }
  txList.add(tx);
}
