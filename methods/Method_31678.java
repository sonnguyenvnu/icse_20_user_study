/** 
 * Retrieves the baseline marker from the schema history table.
 * @return The baseline marker or {@code null} if none could be found.
 */
public final AppliedMigration getBaselineMarker(){
  List<AppliedMigration> appliedMigrations=allAppliedMigrations();
  for (int i=0; i < Math.min(appliedMigrations.size(),2); i++) {
    AppliedMigration appliedMigration=appliedMigrations.get(i);
    if (appliedMigration.getType() == MigrationType.BASELINE) {
      return appliedMigration;
    }
  }
  return null;
}
