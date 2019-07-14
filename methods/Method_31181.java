/** 
 * Sets the file name prefix for repeatable sql migrations. <p>Repeatable sql migrations have the following file name structure: prefixSeparatorDESCRIPTIONsuffix , which using the defaults translates to R__My_description.sql</p>
 * @param repeatableSqlMigrationPrefix The file name prefix for repeatable sql migrations (default: R)
 */
public FluentConfiguration repeatableSqlMigrationPrefix(String repeatableSqlMigrationPrefix){
  config.setRepeatableSqlMigrationPrefix(repeatableSqlMigrationPrefix);
  return this;
}
