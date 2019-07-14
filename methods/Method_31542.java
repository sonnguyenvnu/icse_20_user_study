/** 
 * Retrieves the full set of infos about future migrations applied to the DB.
 * @return The future migrations. An empty array if none.
 */
public MigrationInfo[] future(){
  List<MigrationInfo> futureMigrations=new ArrayList<>();
  for (  MigrationInfo migrationInfo : migrationInfos) {
    if (((migrationInfo.getState() == MigrationState.FUTURE_SUCCESS) || (migrationInfo.getState() == MigrationState.FUTURE_FAILED))) {
      futureMigrations.add(migrationInfo);
    }
  }
  return futureMigrations.toArray(new MigrationInfo[0]);
}
