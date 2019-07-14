/** 
 * Resolves table name or alias that will be used in the query.
 */
protected String resolveTable(final String tableRef,final DbEntityDescriptor ded){
  String tableAlias=templateData.getTableAlias(tableRef);
  if (tableAlias != null) {
    return tableAlias;
  }
  return ded.getTableNameForQuery();
}
