/** 
 * Whether to batch SQL statements when executing them. Batching can save up to 99 percent of network roundtrips by sending up to 100 statements at once over the network to the database, instead of sending each statement individually. This is particularly useful for very large SQL migrations composed of multiple MB or even GB of reference data, as this can dramatically reduce the network overhead. This is supported for INSERT, UPDATE, DELETE, MERGE and UPSERT statements. All other statements are automatically executed without batching. <p><i>Flyway Pro and Flyway Enterprise only</i></p>
 * @param batch {@code true} to batch SQL statements. {@code false} to execute them individually instead. (default: {@code false})
 */
public void setBatch(boolean batch){
  throw new org.flywaydb.core.internal.license.FlywayProUpgradeRequiredException("batch");
}
