@Override public void batchSave(ClientReportBean clientReportBean){
  try {
    final long collectTime=clientReportBean.getCollectTime();
    final long reportTime=clientReportBean.getReportTimeStamp();
    final List<Map<String,Object>> datas=clientReportBean.getDatas();
    if (datas == null || datas.isEmpty()) {
      logger.warn("datas field {} is empty",clientReportBean);
      return;
    }
    for (    Map<String,Object> map : datas) {
      Integer clientDataType=MapUtils.getInteger(map,ClientReportConstant.CLIENT_DATA_TYPE,-1);
      ClientCollectDataTypeEnum clientCollectDataTypeEnum=ClientCollectDataTypeEnum.MAP.get(clientDataType);
      if (clientCollectDataTypeEnum == null) {
        continue;
      }
      if (ClientCollectDataTypeEnum.VALUE_LENGTH_DISTRI_TYPE.equals(clientCollectDataTypeEnum)) {
        AppClientValueDistriStatTotal appClientValueDistriStat=generate(collectTime,reportTime,map);
        if (appClientValueDistriStat != null) {
          appClientValueStatDao.save(appClientValueDistriStat);
        }
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
