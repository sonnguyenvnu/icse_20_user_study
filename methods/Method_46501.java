/** 
 * save sql undo log
 * @param groupId    groupId
 * @param unitId     unitId
 * @param sqlType    sqlType
 * @param recordList recordList
 * @throws TxcLogicException ????
 */
private void saveUndoLog(String groupId,String unitId,int sqlType,TableRecordList recordList) throws TxcLogicException {
  UndoLogDO undoLogDO=new UndoLogDO();
  undoLogDO.setRollbackInfo(SqlUtils.objectToBlob(recordList));
  undoLogDO.setUnitId(unitId);
  undoLogDO.setGroupId(groupId);
  undoLogDO.setSqlType(sqlType);
  try {
    txcLogHelper.saveUndoLog(undoLogDO);
  }
 catch (  SQLException e) {
    throw new TxcLogicException(e);
  }
}
