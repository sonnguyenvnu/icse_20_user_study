@Override public void resolveInsertImage(InsertImageParams insertImageParams) throws TxcLogicException {
  List<FieldValue> primaryKeys=new ArrayList<>();
  FieldCluster fieldCluster=new FieldCluster();
  fieldCluster.setPrimaryKeys(primaryKeys);
  ResultSet resultSet=null;
  try {
    resultSet=insertImageParams.getStatement().getGeneratedKeys();
  }
 catch (  SQLException ignored) {
  }
  try {
    for (int i=0; i < insertImageParams.getPrimaryKeyValuesList().size(); i++) {
      Map<String,Object> pks=insertImageParams.getPrimaryKeyValuesList().get(i);
      for (      String key : insertImageParams.getFullyQualifiedPrimaryKeys()) {
        FieldValue fieldValue=new FieldValue();
        fieldValue.setFieldName(key);
        if (pks.containsKey(key)) {
          fieldValue.setValue(pks.get(key));
        }
 else         if (Objects.nonNull(resultSet)) {
          try {
            resultSet.next();
            fieldValue.setValue(resultSet.getObject(1));
          }
 catch (          SQLException ignored) {
          }
        }
        primaryKeys.add(fieldValue);
      }
    }
  }
  finally {
    try {
      DbUtils.close(resultSet);
    }
 catch (    SQLException ignored) {
    }
  }
  TableRecordList tableRecords=new TableRecordList();
  tableRecords.getTableRecords().add(new TableRecord(insertImageParams.getTableName(),fieldCluster));
  saveUndoLog(DTXLocalContext.cur().getGroupId(),DTXLocalContext.cur().getUnitId(),SqlUtils.SQL_TYPE_INSERT,tableRecords);
}
