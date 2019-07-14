@Override public void preUpdate(Update update) throws SQLException {
  Connection connection=(Connection)DTXLocalContext.cur().getResource();
  List<String> columns=new ArrayList<>(update.getColumns().size());
  List<String> primaryKeys=new ArrayList<>(3);
  List<String> tables=new ArrayList<>(update.getTables().size());
  update.getColumns().forEach(column -> {
    column.setTable(update.getTables().get(0));
    columns.add(column.getFullyQualifiedName());
  }
);
  for (  Table table : update.getTables()) {
    tables.add(table.getName());
    TableStruct tableStruct=globalContext.tableStruct(table.getName(),() -> tableStructAnalyser.analyse(connection,table.getName()));
    tableStruct.getPrimaryKeys().forEach(key -> primaryKeys.add(table.getName() + "." + key));
  }
  try {
    UpdateImageParams updateImageParams=new UpdateImageParams();
    updateImageParams.setColumns(columns);
    updateImageParams.setPrimaryKeys(primaryKeys);
    updateImageParams.setTables(tables);
    updateImageParams.setWhereSql(update.getWhere() == null ? "1=1" : update.getWhere().toString());
    txcService.resolveUpdateImage(updateImageParams);
  }
 catch (  TxcLogicException e) {
    throw new SQLException(e.getMessage());
  }
}
