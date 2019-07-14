/** 
 * Sets the file name separator for sql migrations. <p>Sql migrations have the following file name structure: prefixVERSIONseparatorDESCRIPTIONsuffix , which using the defaults translates to V1_1__My_description.sql</p>
 * @param sqlMigrationSeparator The file name separator for sql migrations (default: __)
 */
public FluentConfiguration sqlMigrationSeparator(String sqlMigrationSeparator){
  config.setSqlMigrationSeparator(sqlMigrationSeparator);
  return this;
}
