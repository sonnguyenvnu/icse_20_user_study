@Override public void preDelete(Delete delete) throws SQLException {
  Connection connection=(Connection)DTXLocalContext.cur().getResource();
  if (delete.getTables().size() == 0) {
    delete.setTables(Collections.singletonList(delete.getTable()));
  }
  List<String> tables=new ArrayList<>(delete.getTables().size());
  List<String> primaryKeys=new ArrayList<>(3);
  List<String> columns=new ArrayList<>();
  for (  Table table : delete.getTables()) {
    TableStruct tableStruct=globalContext.tableStruct(table.getName(),() -> tableStructAnalyser.analyse(connection,table.getName()));
    tableStruct.getColumns().forEach((k,v) -> columns.add(tableStruct.getTableName() + SqlUtils.DOT + k));
    tableStruct.getPrimaryKeys().forEach(primaryKey -> primaryKeys.add(tableStruct.getTableName() + SqlUtils.DOT + primaryKey));
    tables.add(tableStruct.getTableName());
  }
  try {
    DeleteImageParams deleteImageParams=new DeleteImageParams();
    deleteImageParams.setColumns(columns);
    deleteImageParams.setPrimaryKeys(primaryKeys);
    deleteImageParams.setTables(tables);
    deleteImageParams.setSqlWhere(delete.getWhere().toString());
    txcService.resolveDeleteImage(deleteImageParams);
  }
 catch (  TxcLogicException e) {
    throw new SQLException(e.getMessage());
  }
}
