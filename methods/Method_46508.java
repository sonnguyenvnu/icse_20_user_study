@Override public void undo(String groupId,String unitId) throws TxcLogicException {
  DTXLocalContext.makeUnProxy();
  List<StatementInfo> statementInfoList=new ArrayList<>();
  try {
    List<UndoLogDO> undoLogDOList=txcLogHelper.getUndoLogByGroupAndUnitId(groupId,unitId);
    for (    UndoLogDO undoLogDO : undoLogDOList) {
      TableRecordList tableRecords=SqlUtils.blobToObject(undoLogDO.getRollbackInfo(),TableRecordList.class);
switch (undoLogDO.getSqlType()) {
case SqlUtils.SQL_TYPE_UPDATE:
        tableRecords.getTableRecords().forEach(tableRecord -> statementInfoList.add(UndoLogAnalyser.update(tableRecord)));
      break;
case SqlUtils.SQL_TYPE_DELETE:
    tableRecords.getTableRecords().forEach(tableRecord -> statementInfoList.add(UndoLogAnalyser.delete(tableRecord)));
  break;
case SqlUtils.SQL_TYPE_INSERT:
tableRecords.getTableRecords().forEach(tableRecord -> statementInfoList.add(UndoLogAnalyser.insert(tableRecord)));
break;
default :
break;
}
}
txcSqlExecutor.applyUndoLog(statementInfoList);
}
 catch (SQLException e) {
TxcLogicException exception=new TxcLogicException(e);
exception.setAttachment(statementInfoList);
throw exception;
}
 finally {
DTXLocalContext.undoProxyStatus();
}
}
