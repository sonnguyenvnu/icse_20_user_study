@Override public Map<Integer,Map<String,List<InstanceCommandStats>>> getStandardStatsList(Long appId,long beginTime,long endTime,List<String> commands){
  if (appId == null) {
    return Collections.emptyMap();
  }
  List<InstanceInfo> list=instanceDao.getInstListByAppId(appId);
  if (list == null || list.isEmpty()) {
    return Collections.emptyMap();
  }
  Map<Integer,Map<String,List<InstanceCommandStats>>> resultMap=new LinkedHashMap<Integer,Map<String,List<InstanceCommandStats>>>();
  for (  InstanceInfo instance : list) {
    if (instance.isOffline()) {
      continue;
    }
    int instanceId=instance.getId();
    String ip=instance.getIp();
    int port=instance.getPort();
    int type=instance.getType();
    Boolean isMaster=redisCenter.isMaster(appId,ip,port);
    if (BooleanUtils.isNotTrue(isMaster)) {
      continue;
    }
    List<Map<String,Object>> objectList=this.queryDiffMapList(beginTime,endTime,ip,port,ConstUtils.REDIS);
    ;
    if (objectList != null) {
      Map<String,List<InstanceCommandStats>> commandMap=new LinkedHashMap<String,List<InstanceCommandStats>>();
      for (      String commandName : commands) {
        List<InstanceCommandStats> resultList=new ArrayList<InstanceCommandStats>(objectList.size());
        for (        Map<String,Object> map : objectList) {
          InstanceCommandStats stats=parseCommand(instanceId,commandName,map,false,type);
          if (stats != null) {
            resultList.add(stats);
          }
        }
        commandMap.put(commandName,resultList);
      }
      resultMap.put(instanceId,commandMap);
    }
  }
  return resultMap;
}
