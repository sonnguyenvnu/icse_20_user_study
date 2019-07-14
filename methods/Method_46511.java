@Override public void postInsert(StatementInformation statementInformation) throws SQLException {
  Connection connection=(Connection)DTXLocalContext.cur().getResource();
  Insert insert=(Insert)statementInformation.getAttachment();
  TableStruct tableStruct=globalContext.tableStruct(insert.getTable().getName(),() -> tableStructAnalyser.analyse(connection,insert.getTable().getName()));
  PrimaryKeyListVisitor primaryKeyListVisitor=new PrimaryKeyListVisitor(insert.getTable(),insert.getColumns(),tableStruct.getFullyQualifiedPrimaryKeys());
  insert.getItemsList().accept(primaryKeyListVisitor);
  try {
    InsertImageParams insertImageParams=new InsertImageParams();
    insertImageParams.setTableName(tableStruct.getTableName());
    insertImageParams.setStatement(statementInformation.getStatement());
    insertImageParams.setFullyQualifiedPrimaryKeys(tableStruct.getFullyQualifiedPrimaryKeys());
    insertImageParams.setPrimaryKeyValuesList(primaryKeyListVisitor.getPrimaryKeyValuesList());
    txcService.resolveInsertImage(insertImageParams);
  }
 catch (  TxcLogicException e) {
    throw new SQLException(e);
  }
}
