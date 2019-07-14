/** 
 * Creates UPDATE query that updates all values of an entity that is matched by id.
 */
public DbSqlBuilder updateAll(final Object entity){
  String tableRef=createTableRefName(entity);
  if (!dbOomConfig.isUpdateAcceptsTableAlias()) {
    tableRef=null;
  }
  return sql().$(UPDATE).table(entity,tableRef).setAll(tableRef,entity).$(WHERE).matchIds(tableRef,entity);
}
