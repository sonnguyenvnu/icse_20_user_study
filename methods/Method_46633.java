/** 
 * Manager ??TCC??????
 * @param groupId groupId
 * @param unitId  unitId
 * @param state   state
 */
public void reportTccCleanException(String groupId,String unitId,int state){
  TxExceptionParams txExceptionParams=new TxExceptionParams();
  txExceptionParams.setGroupId(groupId);
  txExceptionParams.setRegistrar(TxExceptionParams.TCC_CLEAN_ERROR);
  txExceptionParams.setTransactionState(state);
  txExceptionParams.setUnitId(unitId);
  report(txExceptionParams);
}
