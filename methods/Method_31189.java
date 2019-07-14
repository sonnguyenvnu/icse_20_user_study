/** 
 * Whether to Flyway's support for Oracle SQL*Plus commands should be activated. <p><i>Flyway Pro and Flyway Enterprise only</i></p>
 * @param oracleSqlplus {@code true} to active SQL*Plus support. {@code false} to fail fast instead. (default: {@code false})
 */
public FluentConfiguration oracleSqlplus(boolean oracleSqlplus){
  config.setOracleSqlplus(oracleSqlplus);
  return this;
}
