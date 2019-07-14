/** 
 * Appends alias.
 */
protected void appendAlias(final StringBuilder query,final DbEntityDescriptor ded,final DbEntityColumnDescriptor dec){
  final ColumnAliasType columnAliasType=templateData.getColumnAliasType();
  if (columnAliasType == null || columnAliasType == ColumnAliasType.TABLE_REFERENCE) {
    final String tableName=ded.getTableName();
    final String columnName=dec.getColumnNameForQuery();
    templateData.registerColumnDataForTableRef(tableRef,tableName);
    query.append(tableRef).append(columnAliasSeparator).append(columnName);
  }
 else   if (columnAliasType == ColumnAliasType.COLUMN_CODE) {
    final String tableName=ded.getTableName();
    final String columnName=dec.getColumnName();
    final String code=templateData.registerColumnDataForColumnCode(tableName,columnName);
    query.append(code);
  }
 else   if (columnAliasType == ColumnAliasType.TABLE_NAME) {
    final String tableName=ded.getTableNameForQuery();
    final String columnName=dec.getColumnNameForQuery();
    query.append(tableName).append(columnAliasSeparator).append(columnName);
  }
}
