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
    List<AppClientExceptionStat> appClientExceptionStatList=new ArrayList<AppClientExceptionStat>();
    for (    Map<String,Object> map : datas) {
      Integer clientDataType=MapUtils.getInteger(map,ClientReportConstant.CLIENT_DATA_TYPE,-1);
      ClientCollectDataTypeEnum clientCollectDataTypeEnum=ClientCollectDataTypeEnum.MAP.get(clientDataType);
      if (clientCollectDataTypeEnum == null) {
        continue;
      }
      if (ClientCollectDataTypeEnum.EXCEPTION_TYPE.equals(clientCollectDataTypeEnum)) {
        AppClientExceptionStat appClientExceptionStat=generate(clientIp,collectTime,reportTime,map);
        if (appClientExceptionStat != null) {
          appClientExceptionStatList.add(appClientExceptionStat);
        }
      }
    }
    if (CollectionUtils.isNotEmpty(appClientExceptionStatList)) {
      appClientExceptionStatDao.batchSave(appClientExceptionStatList);
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
