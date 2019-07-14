/** 
 * Refreshes the info about all known migrations from both the classpath and the DB.
 */
public void refresh(){
  Collection<ResolvedMigration> resolvedMigrations=migrationResolver.resolveMigrations(context);
  List<AppliedMigration> appliedMigrations=schemaHistory.allAppliedMigrations();
  MigrationInfoContext context=new MigrationInfoContext();
  context.outOfOrder=outOfOrder;
  context.pending=pending;
  context.missing=missing;
  context.ignored=ignored;
  context.future=future;
  context.target=target;
  Map<Pair<MigrationVersion,Boolean>,ResolvedMigration> resolvedVersioned=new TreeMap<>();
  Map<String,ResolvedMigration> resolvedRepeatable=new TreeMap<>();
  for (  ResolvedMigration resolvedMigration : resolvedMigrations) {
    MigrationVersion version=resolvedMigration.getVersion();
    if (version != null) {
      if (version.compareTo(context.lastResolved) > 0) {
        context.lastResolved=version;
      }
      resolvedVersioned.put(Pair.of(version,false),resolvedMigration);
    }
 else {
      resolvedRepeatable.put(resolvedMigration.getDescription(),resolvedMigration);
    }
  }
  List<Pair<AppliedMigration,AppliedMigrationAttributes>> appliedVersioned=new ArrayList<>();
  List<AppliedMigration> appliedRepeatable=new ArrayList<>();
  for (  AppliedMigration appliedMigration : appliedMigrations) {
    MigrationVersion version=appliedMigration.getVersion();
    if (version == null) {
      appliedRepeatable.add(appliedMigration);
      continue;
    }
    if (appliedMigration.getType() == MigrationType.SCHEMA) {
      context.schema=version;
    }
    if (appliedMigration.getType() == MigrationType.BASELINE) {
      context.baseline=version;
    }
    appliedVersioned.add(Pair.of(appliedMigration,new AppliedMigrationAttributes()));
  }
  for (  Pair<AppliedMigration,AppliedMigrationAttributes> av : appliedVersioned) {
    MigrationVersion version=av.getLeft().getVersion();
    if (version != null) {
      if (version.compareTo(context.lastApplied) > 0) {
        context.lastApplied=version;
      }
 else {
        av.getRight().outOfOrder=true;
      }
    }
  }
  if (MigrationVersion.CURRENT == target) {
    context.target=context.lastApplied;
  }
  List<MigrationInfoImpl> migrationInfos1=new ArrayList<>();
  Set<ResolvedMigration> pendingResolvedVersioned=new HashSet<>(resolvedVersioned.values());
  for (  Pair<AppliedMigration,AppliedMigrationAttributes> av : appliedVersioned) {
    ResolvedMigration resolvedMigration=resolvedVersioned.get(Pair.of(av.getLeft().getVersion(),av.getLeft().getType().isUndo()));
    if (resolvedMigration != null) {
      pendingResolvedVersioned.remove(resolvedMigration);
    }
    migrationInfos1.add(new MigrationInfoImpl(resolvedMigration,av.getLeft(),context,av.getRight().outOfOrder));
  }
  for (  ResolvedMigration prv : pendingResolvedVersioned) {
    migrationInfos1.add(new MigrationInfoImpl(prv,null,context,false));
  }
  for (  AppliedMigration appliedRepeatableMigration : appliedRepeatable) {
    if (!context.latestRepeatableRuns.containsKey(appliedRepeatableMigration.getDescription()) || (appliedRepeatableMigration.getInstalledRank() > context.latestRepeatableRuns.get(appliedRepeatableMigration.getDescription()))) {
      context.latestRepeatableRuns.put(appliedRepeatableMigration.getDescription(),appliedRepeatableMigration.getInstalledRank());
    }
  }
  Set<ResolvedMigration> pendingResolvedRepeatable=new HashSet<>(resolvedRepeatable.values());
  for (  AppliedMigration appliedRepeatableMigration : appliedRepeatable) {
    ResolvedMigration resolvedMigration=resolvedRepeatable.get(appliedRepeatableMigration.getDescription());
    int latestRank=context.latestRepeatableRuns.get(appliedRepeatableMigration.getDescription());
    if (resolvedMigration != null && appliedRepeatableMigration.getInstalledRank() == latestRank && Objects.equals(appliedRepeatableMigration.getChecksum(),resolvedMigration.getChecksum())) {
      pendingResolvedRepeatable.remove(resolvedMigration);
    }
    migrationInfos1.add(new MigrationInfoImpl(resolvedMigration,appliedRepeatableMigration,context,false));
  }
  for (  ResolvedMigration prr : pendingResolvedRepeatable) {
    migrationInfos1.add(new MigrationInfoImpl(prr,null,context,false));
  }
  Collections.sort(migrationInfos1);
  migrationInfos=migrationInfos1;
}
