/** 
 * Manager ??????
 * @param groupId   groupId
 * @param unitId    unitId
 * @param registrar registrar
 * @param state     transactionState
 */
public void reportTransactionState(String groupId,String unitId,Short registrar,int state){
  TxExceptionParams txExceptionParams=new TxExceptionParams();
  txExceptionParams.setGroupId(groupId);
  txExceptionParams.setRegistrar(registrar);
  txExceptionParams.setTransactionState(state);
  txExceptionParams.setUnitId(unitId);
  report(txExceptionParams);
}
