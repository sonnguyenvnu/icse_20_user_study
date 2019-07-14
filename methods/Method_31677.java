/** 
 * Checks whether the schema history table contains at least one non-synthetic applied migration.
 * @return {@code true} if it does, {@code false} if it doesn't.
 */
public final boolean hasNonSyntheticAppliedMigrations(){
  for (  AppliedMigration appliedMigration : allAppliedMigrations()) {
    if (!appliedMigration.getType().isSynthetic()) {
      return true;
    }
  }
  return false;
}
