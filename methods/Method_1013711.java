@Override public void refresh(){
  int setUpNewSuccessCnt=0;
  int currentlyWorkingCnt=0;
  List<MigrationShard> migrationShards=getHolder().getMigrationShards();
  final int migrationShardsSize=migrationShards.size();
  for (  MigrationShard migrationShard : migrationShards) {
    if (migrationShard.getShardMigrationResult().stepTerminated(ShardMigrationStep.MIGRATE_NEW_PRIMARY_DC)) {
      if (migrationShard.getShardMigrationResult().stepSuccess(ShardMigrationStep.MIGRATE_NEW_PRIMARY_DC)) {
        ++setUpNewSuccessCnt;
      }
    }
 else {
      ++currentlyWorkingCnt;
    }
  }
  if (currentlyWorkingCnt == 0) {
    if (setUpNewSuccessCnt == migrationShardsSize) {
      int finishedCnt=0;
      for (      MigrationShard migrationShard : migrationShards) {
        if (migrationShard.getShardMigrationResult().stepTerminated(ShardMigrationStep.MIGRATE)) {
          ++finishedCnt;
        }
      }
      if (0 == finishedCnt && doOtherDcMigrate.compareAndSet(false,true)) {
        doMigrateOtherDc();
      }
 else       if (finishedCnt == migrationShardsSize) {
        logger.info("[success][continue]{}",getHolder().clusterName());
        updateAndProcess(nextAfterSuccess());
      }
    }
 else {
      logger.info("[fail]{}",getHolder().clusterName());
      if (this instanceof MigrationMigratingState) {
        updateAndProcess(nextAfterFail());
        return;
      }
      if (this instanceof MigrationPartialSuccessState) {
        updateAndStop(nextAfterFail());
        return;
      }
    }
  }
}
