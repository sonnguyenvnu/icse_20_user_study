/** 
 * Whether to stream SQL migrations when executing them. Streaming doesn't load the entire migration in memory at once. Instead each statement is loaded individually. This is particularly useful for very large SQL migrations composed of multiple MB or even GB of reference data, as this dramatically reduces Flyway's memory consumption. <p><i>Flyway Pro and Flyway Enterprise only</i></p>
 * @param stream {@code true} to stream SQL migrations. {@code false} to fully loaded them in memory instead. (default: {@code false})
 */
public void setStream(boolean stream){
  throw new org.flywaydb.core.internal.license.FlywayProUpgradeRequiredException("stream");
}
