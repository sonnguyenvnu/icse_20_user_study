/** 
 * Creates DELETE query that deletes entity by ID.
 */
public DbSqlBuilder deleteById(final Object entity){
  final String tableRef=createTableRefName(entity);
  return sql().$(DELETE_FROM).table(entity,null,tableRef).$(WHERE).matchIds(tableRef,entity);
}
