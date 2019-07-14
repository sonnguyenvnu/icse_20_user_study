/** 
 * Baselines the database.
 */
public void baseline(){
  callbackExecutor.onEvent(Event.BEFORE_BASELINE);
  try {
    if (!schemaHistory.exists()) {
      schemaHistory.create(true);
    }
 else {
      AppliedMigration baselineMarker=schemaHistory.getBaselineMarker();
      if (baselineMarker != null) {
        if (baselineVersion.equals(baselineMarker.getVersion()) && baselineDescription.equals(baselineMarker.getDescription())) {
          LOG.info("Schema history table " + schemaHistory + " already initialized with (" + baselineVersion + "," + baselineDescription + "). Skipping.");
        }
 else {
          throw new FlywayException("Unable to baseline schema history table " + schemaHistory + " with (" + baselineVersion + "," + baselineDescription + ") as it has already been baselined with (" + baselineMarker.getVersion() + "," + baselineMarker.getDescription() + ")");
        }
      }
 else {
        if (schemaHistory.hasSchemasMarker() && baselineVersion.equals(MigrationVersion.fromVersion("0"))) {
          throw new FlywayException("Unable to baseline schema history table " + schemaHistory + " with version 0 as this version was used for schema creation");
        }
        if (schemaHistory.hasNonSyntheticAppliedMigrations()) {
          throw new FlywayException("Unable to baseline schema history table " + schemaHistory + " as it already contains migrations");
        }
      }
    }
  }
 catch (  FlywayException e) {
    callbackExecutor.onEvent(Event.AFTER_BASELINE_ERROR);
    throw e;
  }
  LOG.info("Successfully baselined schema with version: " + baselineVersion);
  callbackExecutor.onEvent(Event.AFTER_BASELINE);
}
