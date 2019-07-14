/** 
 * Returns all records for given type.
 */
public DbSqlBuilder findAll(final Object entity){
  final String tableRef=createTableRefName(entity);
  return sql().$(SELECT).column(tableRef).$(FROM).table(entity,tableRef);
}
