protected void onTable(final DbSqlBuilder sqlBuilder,final String allTables){
  String[] tables=StringUtil.split(allTables,StringPool.COMMA);
  for (  String table : tables) {
    sqlBuilder.table(table);
  }
}
