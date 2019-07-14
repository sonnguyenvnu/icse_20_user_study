private AppClientValueDistriStatTotal generate(long collectTime,long reportTime,Map<String,Object> map){
  String valueDistri=MapUtils.getString(map,ClientReportConstant.VALUE_DISTRI,"");
  ValueSizeDistriEnum valueSizeDistriEnum=ValueSizeDistriEnum.getByValue(valueDistri);
  if (valueSizeDistriEnum == null) {
    logger.warn("valueDistri {} is wrong, not in enums {}",valueDistri,ValueSizeDistriEnum.values());
  }
  Integer count=MapUtils.getInteger(map,ClientReportConstant.VALUE_COUNT,0);
  String command=MapUtils.getString(map,ClientReportConstant.VALUE_COMMAND,"");
  if (StringUtils.isBlank(command)) {
    logger.warn("command is empty!");
    return null;
  }
  if (excludeCommands.contains(command)) {
    return null;
  }
  String hostPort=MapUtils.getString(map,ClientReportConstant.VALUE_HOST_PORT,"");
  if (StringUtils.isEmpty(hostPort)) {
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
  AppClientValueDistriStatTotal stat=new AppClientValueDistriStatTotal();
  stat.setAppId(instanceInfo.getAppId());
  stat.setCollectTime(collectTime);
  stat.setUpdateTime(new Date());
  stat.setCommand(command);
  stat.setDistributeType(valueSizeDistriEnum.getType());
  stat.setCount(count);
  return stat;
}
