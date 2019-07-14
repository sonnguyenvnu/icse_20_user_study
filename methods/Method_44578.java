public PendingTransaction[] getTransactions(){
  PendingTransaction[] copy=new PendingTransaction[pending.length];
  System.arraycopy(pending,0,copy,0,pending.length);
  return copy;
}
