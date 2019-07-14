/** 
 * Creates SELECT COUNT criteria for the entity matched by non-null values.
 */
public DbSqlBuilder count(final Object entity){
  final String tableRef=createTableRefName(entity);
  return sql().$(SELECT_COUNT_1_FROM).table(entity,tableRef).$(WHERE).match(tableRef,entity);
}
