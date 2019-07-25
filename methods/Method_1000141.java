public void init(){
  fetchExecutor.scheduleWithFixedDelay(() -> {
    try {
      if (fetchFlag) {
        fetchFlag=false;
        startFetchSyncBlock();
      }
    }
 catch (    Throwable t) {
      logger.error("Fetch sync block error.",t);
    }
  }
,10,1,TimeUnit.SECONDS);
  blockHandleExecutor.scheduleWithFixedDelay(() -> {
    try {
      if (handleFlag) {
        handleFlag=false;
        handleSyncBlock();
      }
    }
 catch (    Throwable t) {
      logger.error("Handle sync block error.",t);
    }
  }
,10,1,TimeUnit.SECONDS);
}
