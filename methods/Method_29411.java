@Override public void monitorLastMinuteAllInstanceInfo(){
  long startTime=System.currentTimeMillis();
  List<InstanceAlertConfig> commonInstanceAlertConfigList=getByType(InstanceAlertTypeEnum.ALL_ALERT.getValue());
  List<InstanceAlertConfig> specialInstanceAlertConfigList=getByType(InstanceAlertTypeEnum.INSTANCE_ALERT.getValue());
  List<InstanceAlertConfig> allInstanceAlertConfigList=new ArrayList<InstanceAlertConfig>();
  allInstanceAlertConfigList.addAll(commonInstanceAlertConfigList);
  allInstanceAlertConfigList.addAll(specialInstanceAlertConfigList);
  if (CollectionUtils.isEmpty(allInstanceAlertConfigList)) {
    return;
  }
  List<InstanceInfo> allInstanceInfoList=instanceDao.getAllInsts();
  if (CollectionUtils.isEmpty(allInstanceInfoList)) {
    return;
  }
  Date currentTime=new Date();
  Date beginTime=DateUtils.addMinutes(currentTime,-2);
  Date endTime=DateUtils.addMinutes(currentTime,-1);
  Map<String,StandardStats> standardStatMap=getStandardStatsMap(beginTime,endTime);
  if (MapUtils.isEmpty(standardStatMap)) {
    logger.warn("standardStatMap is empty!");
    return;
  }
  List<InstanceAlertValueResult> instanceAlertValueResultList=new ArrayList<InstanceAlertValueResult>();
  for (  InstanceAlertConfig instanceAlertConfig : allInstanceAlertConfigList) {
    if (!checkInCycle(instanceAlertConfig)) {
      continue;
    }
    List<InstanceInfo> tempInstanceInfoList=allInstanceInfoList;
    if (instanceAlertConfig.isSpecail()) {
      tempInstanceInfoList.clear();
      InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceAlertConfig.getInstanceId());
      if (instanceInfo == null) {
        continue;
      }
      tempInstanceInfoList.add(instanceInfo);
    }
    for (    InstanceInfo instanceInfo : tempInstanceInfoList) {
      List<InstanceAlertValueResult> InstanceAlertValueResultTempList=dealInstanceAlert(specialInstanceAlertConfigList,instanceAlertConfig,instanceInfo,standardStatMap,currentTime);
      if (CollectionUtils.isNotEmpty(InstanceAlertValueResultTempList)) {
        instanceAlertValueResultList.addAll(InstanceAlertValueResultTempList);
      }
    }
    updateLastCheckTime(instanceAlertConfig.getId(),currentTime);
  }
  if (CollectionUtils.isNotEmpty(instanceAlertValueResultList)) {
    sendInstanceAlertEmail(beginTime,endTime,instanceAlertValueResultList);
  }
  long costTime=System.currentTimeMillis() - startTime;
  if (costTime > 20000) {
    logger.warn("monitorLastMinuteAllInstanceInfo cost {} ms",costTime);
  }
}
