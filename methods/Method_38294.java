/** 
 * Creates DELETE query that truncates all table data.
 */
public DbSqlBuilder truncate(final Object entity){
  return sql().$(DELETE_FROM).table(entity,null);
}
