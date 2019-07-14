private AppClientCostTimeStat generate(String clientIp,long collectTime,long reportTime,Map<String,Object> map){
  try {
    Integer count=MapUtils.getInteger(map,ClientReportConstant.COST_COUNT,0);
    String command=MapUtils.getString(map,ClientReportConstant.COST_COMMAND,"");
    if (StringUtils.isBlank(command)) {
      logger.warn("command is empty!");
      return null;
    }
    String hostPort=MapUtils.getString(map,ClientReportConstant.COST_HOST_PORT,"");
    if (StringUtils.isBlank(hostPort)) {
      logger.warn("hostPort is empty",hostPort);
      return null;
    }
    int index=hostPort.indexOf(":");
    if (index <= 0) {
      logger.warn("hostPort {} format is wrong",hostPort);
      return null;
    }
    String host=hostPort.substring(0,index);
    int port=NumberUtils.toInt(hostPort.substring(index + 1));
    InstanceInfo instanceInfo=clientReportInstanceService.getInstanceInfoByHostPort(host,port);
    if (instanceInfo == null) {
      return null;
    }
    long appId=instanceInfo.getAppId();
    double mean=MapUtils.getDouble(map,ClientReportConstant.COST_TIME_MEAN,0.0);
    Integer median=MapUtils.getInteger(map,ClientReportConstant.COST_TIME_MEDIAN,0);
    Integer ninetyPercentMax=MapUtils.getInteger(map,ClientReportConstant.COST_TIME_90_MAX,0);
    Integer ninetyNinePercentMax=MapUtils.getInteger(map,ClientReportConstant.COST_TIME_99_MAX,0);
    Integer hunredMax=MapUtils.getInteger(map,ClientReportConstant.COST_TIME_100_MAX,0);
    AppClientCostTimeStat stat=new AppClientCostTimeStat();
    stat.setAppId(appId);
    stat.setClientIp(clientIp);
    stat.setReportTime(new Date(reportTime));
    stat.setCollectTime(collectTime);
    stat.setCreateTime(new Date());
    stat.setCommand(command);
    stat.setCount(count);
    stat.setInstanceHost(host);
    stat.setInstancePort(port);
    stat.setMean(NumberUtils.toDouble(new DecimalFormat("#.00").format(mean)));
    stat.setMedian(median);
    stat.setNinetyPercentMax(ninetyPercentMax);
    stat.setNinetyNinePercentMax(ninetyNinePercentMax);
    stat.setHundredMax(hunredMax);
    stat.setInstanceId(instanceInfo.getId());
    return stat;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return null;
  }
}
