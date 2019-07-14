private String toMigrationText(MigrationInfoImpl migration,boolean isOutOfOrder){
  final MigrationExecutor migrationExecutor=migration.getResolvedMigration().getExecutor();
  final String migrationText;
  if (migration.getVersion() != null) {
    migrationText="schema " + schema + " to version " + migration.getVersion() + (StringUtils.hasLength(migration.getDescription()) ? " - " + migration.getDescription() : "") + (isOutOfOrder ? " [out of order]" : "") + (migrationExecutor.canExecuteInTransaction() ? "" : " [non-transactional]");
  }
 else {
    migrationText="schema " + schema + " with repeatable migration " + migration.getDescription() + (migrationExecutor.canExecuteInTransaction() ? "" : " [non-transactional]");
  }
  return migrationText;
}
