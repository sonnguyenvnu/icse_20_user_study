/** 
 * Retrieves the full set of infos about out of order migrations applied to the DB.
 * @return The out of order migrations. An empty array if none.
 */
public MigrationInfo[] outOfOrder(){
  List<MigrationInfo> outOfOrderMigrations=new ArrayList<>();
  for (  MigrationInfo migrationInfo : migrationInfos) {
    if (migrationInfo.getState() == MigrationState.OUT_OF_ORDER) {
      outOfOrderMigrations.add(migrationInfo);
    }
  }
  return outOfOrderMigrations.toArray(new MigrationInfo[0]);
}
