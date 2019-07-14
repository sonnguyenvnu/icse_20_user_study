/** 
 * Creates UPDATE query that updates all non-null values of an entity that is matched by id.
 */
public DbSqlBuilder update(final Object entity){
  String tableRef=createTableRefName(entity);
  if (!dbOomConfig.isUpdateAcceptsTableAlias()) {
    tableRef=null;
  }
  return sql().$(UPDATE).table(entity,tableRef).set(tableRef,entity).$(WHERE).matchIds(tableRef,entity);
}
