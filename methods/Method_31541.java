/** 
 * Retrieves the full set of infos about the migrations resolved on the classpath.
 * @return The resolved migrations. An empty array if none.
 */
public MigrationInfo[] resolved(){
  List<MigrationInfo> resolvedMigrations=new ArrayList<>();
  for (  MigrationInfo migrationInfo : migrationInfos) {
    if (migrationInfo.getState().isResolved()) {
      resolvedMigrations.add(migrationInfo);
    }
  }
  return resolvedMigrations.toArray(new MigrationInfo[0]);
}
