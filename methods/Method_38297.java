/** 
 * Creates UPDATE query for single column of an entity that is matched by id.
 */
public DbSqlBuilder updateColumn(final Object entity,final String columnRef,final Object value){
  String tableRef=createTableRefName(entity);
  if (!dbOomConfig.isUpdateAcceptsTableAlias()) {
    tableRef=null;
  }
  return sql().$(UPDATE).table(entity,tableRef).$(SET).ref(null,columnRef).$(EQUALS).columnValue(value).$(WHERE).matchIds(tableRef,entity);
}
