private List<AppCommandStats> getCommandStatsList(long appId,long collectTime,Table<RedisConstant,String,Long> table){
  Map<String,Long> commandMap=table.row(RedisConstant.Commandstats);
  List<AppCommandStats> list=new ArrayList<AppCommandStats>();
  if (commandMap == null) {
    return list;
  }
  for (  String key : commandMap.keySet()) {
    String commandName=key.replace("cmdstat_","");
    long callCount=MapUtils.getLong(commandMap,key,0L);
    if (callCount == 0L) {
      continue;
    }
    AppCommandStats commandStats=new AppCommandStats();
    commandStats.setAppId(appId);
    commandStats.setCollectTime(collectTime);
    commandStats.setCommandName(commandName);
    commandStats.setCommandCount(callCount);
    commandStats.setModifyTime(new Date());
    list.add(commandStats);
  }
  return list;
}
