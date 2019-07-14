@Override public void update(AppliedMigration appliedMigration,ResolvedMigration resolvedMigration){
  connection.restoreOriginalState();
  clearCache();
  MigrationVersion version=appliedMigration.getVersion();
  String description=resolvedMigration.getDescription();
  Integer checksum=resolvedMigration.getChecksum();
  MigrationType type=appliedMigration.getType().isSynthetic() ? appliedMigration.getType() : resolvedMigration.getType();
  LOG.info("Repairing Schema History table for version " + version + " (Description: " + description + ", Type: " + type + ", Checksum: " + checksum + ")  ...");
  try {
    jdbcTemplate.update("UPDATE " + table + " SET " + database.quote("description") + "=? , " + database.quote("type") + "=? , " + database.quote("checksum") + "=?" + " WHERE " + database.quote("version") + "=?",description,type,checksum,version);
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to repair Schema History table " + table + " for version " + version,e);
  }
}
