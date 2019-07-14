/** 
 * Creates SELECT criteria for the entity matched by all values.
 */
public DbSqlBuilder findByAll(final Object entity){
  final String tableRef=createTableRefName(entity);
  return sql().$(SELECT).column(tableRef).$(FROM).table(entity,tableRef).$(WHERE).matchAll(tableRef,entity);
}
