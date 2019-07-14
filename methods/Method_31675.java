@Override public void removeFailedMigrations(){
  if (!exists()) {
    LOG.info("Repair of failed migration in Schema History table " + table + " not necessary as table doesn't exist.");
    return;
  }
  boolean failed=false;
  List<AppliedMigration> appliedMigrations=allAppliedMigrations();
  for (  AppliedMigration appliedMigration : appliedMigrations) {
    if (!appliedMigration.isSuccess()) {
      failed=true;
    }
  }
  if (!failed) {
    LOG.info("Repair of failed migration in Schema History table " + table + " not necessary. No failed migration detected.");
    return;
  }
  try {
    clearCache();
    jdbcTemplate.execute("DELETE FROM " + table + " WHERE " + database.quote("success") + " = " + database.getBooleanFalse());
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to repair Schema History table " + table,e);
  }
}
