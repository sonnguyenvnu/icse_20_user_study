@Override public void lockSelect(SelectImageParams selectImageParams,boolean isxLock) throws TxcLogicException {
  Connection connection=(Connection)DTXLocalContext.cur().getResource();
  try {
    List<ModifiedRecord> modifiedRecords=txcSqlExecutor.selectSqlPreviousPrimaryKeys(connection,selectImageParams);
    Set<String> lockIdSet=new HashSet<>();
    for (    ModifiedRecord modifiedRecord : modifiedRecords) {
      for (      Map.Entry<String,FieldCluster> entry : modifiedRecord.getFieldClusters().entrySet()) {
        FieldCluster v=entry.getValue();
        lockIdSet.add(hex(v.getPrimaryKeys().toString()));
      }
    }
    lockDataLine(DTXLocalContext.cur().getGroupId(),DTXLocalContext.cur().getUnitId(),lockIdSet,isxLock);
  }
 catch (  SQLException e) {
    throw new TxcLogicException(e);
  }
}
