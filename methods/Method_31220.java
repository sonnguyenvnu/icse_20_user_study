/** 
 * Starts the actual migration.
 * @return The number of successfully applied migrations.
 * @throws FlywayException when migration failed.
 */
public int migrate() throws FlywayException {
  callbackExecutor.onMigrateOrUndoEvent(Event.BEFORE_MIGRATE);
  int count;
  try {
    StopWatch stopWatch=new StopWatch();
    stopWatch.start();
    count=configuration.isGroup() ? schemaHistory.lock(new Callable<Integer>(){
      @Override public Integer call(){
        return migrateAll();
      }
    }
) : migrateAll();
    stopWatch.stop();
    logSummary(count,stopWatch.getTotalTimeMillis());
  }
 catch (  FlywayException e) {
    callbackExecutor.onMigrateOrUndoEvent(Event.AFTER_MIGRATE_ERROR);
    throw e;
  }
  callbackExecutor.onMigrateOrUndoEvent(Event.AFTER_MIGRATE);
  return count;
}
