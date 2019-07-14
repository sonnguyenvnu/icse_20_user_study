private void onAskTransactionStateException(String groupId,String unitId,String transactionType){
  try {
    tmReporter.reportTransactionState(groupId,unitId,TxExceptionParams.ASK_ERROR,0);
    log.warn("{} > has compensation info!",transactionType);
    transactionCleanTemplate.cleanWithoutAspectLog(groupId,unitId,transactionType,0);
  }
 catch (  TransactionClearException e) {
    txLogger.error(groupId,unitId,Transactions.TAG_TASK,"{} > clean transaction error.",transactionType);
  }
}
