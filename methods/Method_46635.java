public void reportTxcUndoException(String groupId,String unitId,List<StatementInfo> statementInfoList){
  TxExceptionParams exceptionParams=new TxExceptionParams();
  exceptionParams.setGroupId(groupId);
  exceptionParams.setUnitId(unitId);
  exceptionParams.setRegistrar(TxExceptionParams.TXC_UNDO_ERROR);
  exceptionParams.setTransactionState(0);
  exceptionParams.setRemark(statementInfoList.toString());
  report(exceptionParams);
}
