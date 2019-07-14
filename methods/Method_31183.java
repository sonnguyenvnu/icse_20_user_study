/** 
 * The file name suffixes for SQL migrations. (default: .sql) <p>SQL migrations have the following file name structure: prefixVERSIONseparatorDESCRIPTIONsuffix , which using the defaults translates to V1_1__My_description.sql</p> <p>Multiple suffixes (like .sql,.pkg,.pkb) can be specified for easier compatibility with other tools such as editors with specific file associations.</p>
 * @param sqlMigrationSuffixes The file name suffixes for SQL migrations.
 */
public FluentConfiguration sqlMigrationSuffixes(String... sqlMigrationSuffixes){
  config.setSqlMigrationSuffixes(sqlMigrationSuffixes);
  return this;
}
