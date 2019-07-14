public static ClientDataCollectReportExecutor getInstance(){
  if (jedisDataCollectAndReportExecutor == null) {
synchronized (ClientDataCollectReportExecutor.class) {
      if (jedisDataCollectAndReportExecutor == null) {
        jedisDataCollectAndReportExecutor=new ClientDataCollectReportExecutor();
      }
    }
  }
  return jedisDataCollectAndReportExecutor;
}
