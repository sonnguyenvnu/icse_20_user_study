/** 
 * ???????
 * @param modifiedRecords ??????
 * @param sqlType         SQL ??
 * @throws TxcLogicException ????
 */
private void resolveModifiedRecords(List<ModifiedRecord> modifiedRecords,int sqlType) throws TxcLogicException {
  TableRecordList tableRecords=new TableRecordList();
  Set<String> lockIdSet=new HashSet<>();
  for (  ModifiedRecord modifiedRecord : modifiedRecords) {
    for (    Map.Entry<String,FieldCluster> entry : modifiedRecord.getFieldClusters().entrySet()) {
      TableRecord tableRecord=new TableRecord();
      tableRecord.setTableName(entry.getKey());
      tableRecord.setFieldCluster(entry.getValue());
      tableRecords.getTableRecords().add(tableRecord);
      lockIdSet.add(hex(tableRecord.getFieldCluster().getPrimaryKeys().toString()));
    }
  }
  if (lockIdSet.isEmpty()) {
    return;
  }
  String groupId=DTXLocalContext.cur().getGroupId();
  String unitId=DTXLocalContext.cur().getUnitId();
  lockDataLine(groupId,unitId,lockIdSet,true);
  saveUndoLog(groupId,unitId,sqlType,tableRecords);
}
