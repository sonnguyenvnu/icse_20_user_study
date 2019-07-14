private InstanceCommandStats parseCommand(long instanceId,String command,Map<String,Object> commandMap,boolean isCommand,int type){
  Long collectTime=MapUtils.getLong(commandMap,ConstUtils.COLLECT_TIME,null);
  if (collectTime == null) {
    return null;
  }
  Long count;
  if (isCommand) {
    count=MapUtils.getLong(commandMap,"cmdstat_" + command.toLowerCase(),null);
  }
 else {
    count=MapUtils.getLong(commandMap,command.toLowerCase(),null);
  }
  if (count == null) {
    return null;
  }
  InstanceCommandStats stats=new InstanceCommandStats();
  stats.setCommandCount(count);
  stats.setCommandName(command);
  stats.setCollectTime(collectTime);
  stats.setCreateTime(DateUtil.getDateByFormat(String.valueOf(collectTime),"yyyyMMddHHmm"));
  stats.setModifyTime(DateUtil.getDateByFormat(String.valueOf(collectTime),"yyyyMMddHHmm"));
  stats.setInstanceId(instanceId);
  return stats;
}
