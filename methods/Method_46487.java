@Override public void onBusinessCodeSuccess(TxTransactionInfo info,Object result){
  DTXLocalContext.cur().setSysTransactionState(1);
}
