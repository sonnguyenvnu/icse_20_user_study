@Override public void clear(String groupId,int state,String unitId,String unitType) throws TransactionClearException {
  boolean rethrowTxcException=false;
  try {
    if (state == 0) {
      txcService.undo(groupId,unitId);
    }
  }
 catch (  TxcLogicException e) {
    @SuppressWarnings("unchecked") List<StatementInfo> statementInfoList=(List<StatementInfo>)e.getAttachment();
    tmReporter.reportTxcUndoException(groupId,unitId,statementInfoList);
    rethrowTxcException=true;
    log.debug("need compensation !");
  }
  try {
    txcService.cleanTxc(groupId,unitId);
  }
 catch (  TxcLogicException e) {
    throw new TransactionClearException(e);
  }
  if (rethrowTxcException) {
    throw TransactionClearException.needCompensation();
  }
}
