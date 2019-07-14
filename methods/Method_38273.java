/** 
 * Simply appends column name with optional table reference and alias.
 */
protected void appendColumnName(final StringBuilder query,final DbEntityDescriptor ded,final DbEntityColumnDescriptor dec){
  query.append(resolveTable(tableRef,ded)).append('.').append(dec.getColumnName());
  if (templateData.getColumnAliasType() != null) {
    query.append(AS);
switch (templateData.getColumnAliasType()) {
case TABLE_NAME:
{
        final String tableName=ded.getTableNameForQuery();
        query.append(tableName).append(columnAliasSeparator).append(dec.getColumnNameForQuery());
        break;
      }
case TABLE_REFERENCE:
{
      final String tableName=ded.getTableName();
      templateData.registerColumnDataForTableRef(tableRef,tableName);
      query.append(tableRef).append(columnAliasSeparator).append(dec.getColumnNameForQuery());
      break;
    }
case COLUMN_CODE:
{
    final String tableName=ded.getTableName();
    final String code=templateData.registerColumnDataForColumnCode(tableName,dec.getColumnName());
    query.append(code);
    break;
  }
}
}
}
