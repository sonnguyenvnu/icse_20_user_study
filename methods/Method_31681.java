/** 
 * Records a new applied migration.
 * @param version       The target version of this migration.
 * @param description   The description of the migration.
 * @param type          The type of migration (BASELINE, SQL, ...)
 * @param script        The name of the script to execute for this migration, relative to its classpath location.
 * @param checksum      The checksum of the migration. (Optional)
 * @param executionTime The execution time (in millis) of this migration.
 * @param success       Flag indicating whether the migration was successful or not.
 */
public final void addAppliedMigration(MigrationVersion version,String description,MigrationType type,String script,Integer checksum,int executionTime,boolean success){
  int installedRank=type == MigrationType.SCHEMA ? 0 : calculateInstalledRank();
  doAddAppliedMigration(installedRank,version,AbbreviationUtils.abbreviateDescription(description),type,AbbreviationUtils.abbreviateScript(script),checksum,executionTime,success);
}
