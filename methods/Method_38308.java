/** 
 * Creates SELECT COUNT criteria for the entity matched by all values.
 */
public DbSqlBuilder countAll(final Object entity){
  final String tableRef=createTableRefName(entity);
  return sql().$(SELECT_COUNT_1_FROM).table(entity,tableRef).$(WHERE).matchAll(tableRef,entity);
}
