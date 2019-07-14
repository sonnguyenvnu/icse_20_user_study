/** 
 * Creates SELECT criteria for the entity matched by column name
 */
public DbSqlBuilder findByColumn(final Class entity,final String column,final Object value){
  final String tableRef=createTableRefName(entity);
  return sql().$(SELECT).column(tableRef).$(FROM).table(entity,tableRef).$(WHERE).ref(tableRef,column).$(EQUALS).columnValue(value);
}
