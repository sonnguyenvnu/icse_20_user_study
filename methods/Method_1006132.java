/** 
 * Starts the BackupManager which is associated with the given  {@link BibDatabaseContext}. As long as no database file is present in  {@link BibDatabaseContext}, the  {@link BackupManager} will do nothing.
 * @param bibDatabaseContext Associated {@link BibDatabaseContext}
 */
public static BackupManager start(BibDatabaseContext bibDatabaseContext){
  BackupManager backupManager=new BackupManager(bibDatabaseContext);
  backupManager.startBackupTask();
  runningInstances.add(backupManager);
  return backupManager;
}
