@Override public void handleNotifyGroupBusinessException(Object params,Throwable ex){
  List paramList=(List)params;
  String groupId=(String)paramList.get(0);
  int state=(int)paramList.get(1);
  String unitId=(String)paramList.get(2);
  String transactionType=(String)paramList.get(3);
  if (ex instanceof UserRollbackException) {
    state=0;
  }
  if ((ex.getCause() != null && ex.getCause() instanceof UserRollbackException)) {
    state=0;
  }
  try {
    transactionCleanTemplate.clean(groupId,unitId,transactionType,state);
  }
 catch (  TransactionClearException e) {
    txLogger.error(groupId,unitId,"notify group","{} > clean transaction error.",transactionType);
  }
}
