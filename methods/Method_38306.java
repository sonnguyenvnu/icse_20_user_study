/** 
 * Creates SELECT criteria for the entity matched by id.
 */
public DbSqlBuilder findById(final Object entity){
  final String tableRef=createTableRefName(entity);
  return sql().$(SELECT).column(tableRef).$(FROM).table(entity,tableRef).$(WHERE).matchIds(tableRef,entity);
}
