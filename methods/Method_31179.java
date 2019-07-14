/** 
 * Sets the file name prefix for sql migrations. <p>Sql migrations have the following file name structure: prefixVERSIONseparatorDESCRIPTIONsuffix , which using the defaults translates to V1_1__My_description.sql</p>
 * @param sqlMigrationPrefix The file name prefix for sql migrations (default: V)
 */
public FluentConfiguration sqlMigrationPrefix(String sqlMigrationPrefix){
  config.setSqlMigrationPrefix(sqlMigrationPrefix);
  return this;
}
