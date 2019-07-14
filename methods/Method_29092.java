@Override public boolean deal(final ClientReportBean clientReportBean){
  try {
    final String clientIp=clientReportBean.getClientIp();
    final List<Map<String,Object>> datas=clientReportBean.getDatas();
    if (datas == null || datas.isEmpty()) {
      logger.warn("datas field {} is empty",clientReportBean);
      return false;
    }
    String key=getThreadPoolKey() + "_" + clientIp;
    asyncService.submitFuture(getThreadPoolKey(),new KeyCallable<Boolean>(key){
      @Override public Boolean execute(){
        try {
          clientReportCostDistriService.batchSave(clientReportBean);
          clientReportValueDistriService.batchSave(clientReportBean);
          clientReportExceptionService.batchSave(clientReportBean);
          clientReportDataSizeService.save(clientReportBean);
          return true;
        }
 catch (        Exception e) {
          logger.error(e.getMessage(),e);
          return false;
        }
      }
    }
);
    return true;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
}
