/** 
 * ?????????????
 * @param groupId  groupId
 * @param unitId   unitId
 * @param unitType unitType
 * @param state    transactionState
 * @throws TransactionClearException TransactionClearException
 */
public void cleanWithoutAspectLog(String groupId,String unitId,String unitType,int state) throws TransactionClearException {
  try {
    transactionBeanHelper.loadTransactionCleanService(unitType).clear(groupId,state,unitId,unitType);
  }
  finally {
    globalContext.clearGroup(groupId);
    dtxChecking.stopDelayChecking(groupId,unitId);
  }
}
