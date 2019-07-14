public static AppInstanceClientRelation generateFromAppClientCostTimeStat(AppClientCostTimeStat appClientCostTimeStat){
  if (appClientCostTimeStat == null) {
    return null;
  }
 else {
    return new AppInstanceClientRelation(appClientCostTimeStat.getAppId(),appClientCostTimeStat.getClientIp(),appClientCostTimeStat.getInstanceHost(),appClientCostTimeStat.getInstancePort(),appClientCostTimeStat.getInstanceId(),new Date(System.currentTimeMillis()));
  }
}
