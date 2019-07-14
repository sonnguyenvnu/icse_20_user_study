@Override public void batchSave(ClientReportBean clientReportBean){
  try {
    final String clientIp=clientReportBean.getClientIp();
    final long collectTime=clientReportBean.getCollectTime();
    final long reportTime=clientReportBean.getReportTimeStamp();
    final List<Map<String,Object>> datas=clientReportBean.getDatas();
    if (datas == null || datas.isEmpty()) {
      logger.warn("datas field {} is empty",clientReportBean);
      return;
    }
    List<AppClientCostTimeStat> appClientCostTimeStatList=new ArrayList<AppClientCostTimeStat>();
    for (    Map<String,Object> map : datas) {
      Integer clientDataType=MapUtils.getInteger(map,ClientReportConstant.CLIENT_DATA_TYPE,-1);
      ClientCollectDataTypeEnum clientCollectDataTypeEnum=ClientCollectDataTypeEnum.MAP.get(clientDataType);
      if (clientCollectDataTypeEnum == null) {
        continue;
      }
      if (ClientCollectDataTypeEnum.COST_TIME_DISTRI_TYPE.equals(clientCollectDataTypeEnum)) {
        AppClientCostTimeStat appClientCostTimeStat=generate(clientIp,collectTime,reportTime,map);
        if (appClientCostTimeStat != null) {
          appClientCostTimeStatList.add(appClientCostTimeStat);
        }
      }
    }
    if (CollectionUtils.isNotEmpty(appClientCostTimeStatList)) {
      appClientCostTimeStatDao.batchSave(appClientCostTimeStatList);
      List<AppClientCostTimeTotalStat> appClientCostTimeTotalStatList=mergeAppClientCostTimeStat(appClientCostTimeStatList);
      if (CollectionUtils.isNotEmpty(appClientCostTimeTotalStatList)) {
        appClientCostTimeTotalStatDao.batchSave(appClientCostTimeTotalStatList);
      }
      appInstanceClientRelationService.batchSave(appClientCostTimeStatList);
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
