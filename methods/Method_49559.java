@Override public void dropTable(String tableString) throws IOException {
  final TableName tableName=TableName.valueOf(tableString);
  if (!adm.tableExists(tableName)) {
    log.debug("Attempted to drop table {} before it exists (noop)",tableString);
    return;
  }
  if (adm.isTableEnabled(tableName)) {
    adm.disableTable(tableName);
  }
  adm.deleteTable(tableName);
}
