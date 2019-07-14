/** 
 * Calculates the installed rank for the new migration to be inserted.
 * @return The installed rank.
 */
private int calculateInstalledRank(){
  List<AppliedMigration> appliedMigrations=allAppliedMigrations();
  if (appliedMigrations.isEmpty()) {
    return 1;
  }
  return appliedMigrations.get(appliedMigrations.size() - 1).getInstalledRank() + 1;
}
