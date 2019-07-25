private void load(){
  List<Long> unfinishedTasks;
  try {
    unfinishedTasks=migrationEventDao.findAllUnfinished();
  }
 catch (  Exception e) {
    logger.warn("[load]{}",e.getMessage());
    return;
  }
  for (  Long unfinishedId : unfinishedTasks) {
    try {
      logger.info("[load]{}",unfinishedId);
      loadAndAdd(unfinishedId);
    }
 catch (    Exception e) {
      logger.error("[load][fail]" + unfinishedId,e);
    }
  }
}
