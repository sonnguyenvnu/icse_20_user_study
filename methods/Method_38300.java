/** 
 * Creates 'SELECT all FROM entity' part of the SQL query that can be easily extended. Entity is referred with its simple class name.
 */
public DbSqlBuilder from(final Object entity){
  return from(entity,createTableRefName(entity));
}
