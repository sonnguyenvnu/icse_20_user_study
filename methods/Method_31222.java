/** 
 * Migrate a group of one (group = false) or more (group = true) migrations.
 * @param firstRun Where this is the first time this code runs in this migration run.
 * @return The number of newly applied migrations.
 */
private Integer migrateGroup(boolean firstRun){
  MigrationInfoServiceImpl infoService=new MigrationInfoServiceImpl(migrationResolver,schemaHistory,configuration,configuration.getTarget(),configuration.isOutOfOrder(),true,true,true,true);
  infoService.refresh();
  MigrationInfo current=infoService.current();
  MigrationVersion currentSchemaVersion=current == null ? MigrationVersion.EMPTY : current.getVersion();
  if (firstRun) {
    LOG.info("Current version of schema " + schema + ": " + currentSchemaVersion);
    if (configuration.isOutOfOrder()) {
      LOG.warn("outOfOrder mode is active. Migration of schema " + schema + " may not be reproducible.");
    }
  }
  MigrationInfo[] future=infoService.future();
  if (future.length > 0) {
    List<MigrationInfo> resolved=Arrays.asList(infoService.resolved());
    Collections.reverse(resolved);
    if (resolved.isEmpty()) {
      LOG.warn("Schema " + schema + " has version " + currentSchemaVersion + ", but no migration could be resolved in the configured locations !");
    }
 else {
      for (      MigrationInfo migrationInfo : resolved) {
        if (migrationInfo.getVersion() != null) {
          LOG.warn("Schema " + schema + " has a version (" + currentSchemaVersion + ") that is newer than the latest available migration (" + migrationInfo.getVersion() + ") !");
          break;
        }
      }
    }
  }
  MigrationInfo[] failed=infoService.failed();
  if (failed.length > 0) {
    if ((failed.length == 1) && (failed[0].getState() == MigrationState.FUTURE_FAILED) && configuration.isIgnoreFutureMigrations()) {
      LOG.warn("Schema " + schema + " contains a failed future migration to version " + failed[0].getVersion() + " !");
    }
 else {
      if (failed[0].getVersion() == null) {
        throw new FlywayException("Schema " + schema + " contains a failed repeatable migration (" + failed[0].getDescription() + ") !");
      }
      throw new FlywayException("Schema " + schema + " contains a failed migration to version " + failed[0].getVersion() + " !");
    }
  }
  LinkedHashMap<MigrationInfoImpl,Boolean> group=new LinkedHashMap<>();
  for (  MigrationInfoImpl pendingMigration : infoService.pending()) {
    boolean isOutOfOrder=pendingMigration.getVersion() != null && pendingMigration.getVersion().compareTo(currentSchemaVersion) < 0;
    group.put(pendingMigration,isOutOfOrder);
    if (!configuration.isGroup()) {
      break;
    }
  }
  if (!group.isEmpty()) {
    applyMigrations(group);
  }
  return group.size();
}
