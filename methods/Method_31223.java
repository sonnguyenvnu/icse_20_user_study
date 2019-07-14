/** 
 * Logs the summary of this migration run.
 * @param migrationSuccessCount The number of successfully applied migrations.
 * @param executionTime         The total time taken to perform this migration run (in ms).
 */
private void logSummary(int migrationSuccessCount,long executionTime){
  if (migrationSuccessCount == 0) {
    LOG.info("Schema " + schema + " is up to date. No migration necessary.");
    return;
  }
  if (migrationSuccessCount == 1) {
    LOG.info("Successfully applied 1 migration to schema " + schema + " (execution time " + TimeFormat.format(executionTime) + ")");
  }
 else {
    LOG.info("Successfully applied " + migrationSuccessCount + " migrations to schema " + schema + " (execution time " + TimeFormat.format(executionTime) + ")");
  }
}
