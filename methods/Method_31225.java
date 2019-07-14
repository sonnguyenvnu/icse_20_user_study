private boolean alignAppliedMigrationsWithResolvedMigrations(){
  boolean repaired=false;
  for (  MigrationInfo migrationInfo : migrationInfoService.all()) {
    MigrationInfoImpl migrationInfoImpl=(MigrationInfoImpl)migrationInfo;
    ResolvedMigration resolved=migrationInfoImpl.getResolvedMigration();
    AppliedMigration applied=migrationInfoImpl.getAppliedMigration();
    if (resolved != null && resolved.getVersion() != null && applied != null && !applied.getType().isSynthetic() && updateNeeded(resolved,applied)) {
      schemaHistory.update(applied,resolved);
      repaired=true;
    }
  }
  return repaired;
}
