/** 
 * ??????
 * @param info info
 * @return Object
 * @throws Throwable Throwable
 */
public Object transactionRunning(TxTransactionInfo info) throws Throwable {
  String transactionType=info.getTransactionType();
  DTXPropagationState propagationState=propagationResolver.resolvePropagationState(info);
  if (propagationState.isIgnored()) {
    return info.getBusinessCallback().call();
  }
  DTXLocalControl dtxLocalControl=txLcnBeanHelper.loadDTXLocalControl(transactionType,propagationState);
  try {
    Set<String> transactionTypeSet=globalContext.txContext(info.getGroupId()).getTransactionTypes();
    transactionTypeSet.add(transactionType);
    dtxLocalControl.preBusinessCode(info);
    txLogger.txTrace(info.getGroupId(),info.getUnitId(),"pre business code, unit type: {}",transactionType);
    Object result=dtxLocalControl.doBusinessCode(info);
    txLogger.txTrace(info.getGroupId(),info.getUnitId(),"business success");
    dtxLocalControl.onBusinessCodeSuccess(info,result);
    return result;
  }
 catch (  TransactionException e) {
    txLogger.error(info.getGroupId(),info.getUnitId(),"before business code error");
    throw e;
  }
catch (  Throwable e) {
    txLogger.error(info.getGroupId(),info.getUnitId(),Transactions.TAG_TRANSACTION,"business code error");
    dtxLocalControl.onBusinessCodeError(info,e);
    throw e;
  }
 finally {
    dtxLocalControl.postBusinessCode(info);
  }
}
