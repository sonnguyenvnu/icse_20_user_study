/** 
 * Sets the stream where to output the SQL statements of a migration dry run.  {@code null} to execute the SQL statementsdirectly against the database. The stream when be closing when Flyway finishes writing the output. <p><i>Flyway Pro and Flyway Enterprise only</i></p>
 * @param dryRunOutput The output file or {@code null} to execute the SQL statements directly against the database.
 */
public void setDryRunOutput(OutputStream dryRunOutput){
  throw new org.flywaydb.core.internal.license.FlywayProUpgradeRequiredException("dryRunOutput");
}
