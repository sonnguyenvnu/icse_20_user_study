@Override public List<InstanceCommandStats> getCommandStatsList(Long instanceId,long beginTime,long endTime,String commandName){
  if (instanceId == null) {
    return Collections.emptyList();
  }
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
  List<InstanceCommandStats> resultList=new ArrayList<InstanceCommandStats>();
  String ip=instanceInfo.getIp();
  int port=instanceInfo.getPort();
  int type=instanceInfo.getType();
  List<Map<String,Object>> objectList=this.queryDiffMapList(beginTime,endTime,ip,port,ConstUtils.REDIS);
  ;
  if (objectList != null) {
    for (    Map<String,Object> map : objectList) {
      InstanceCommandStats stats=parseCommand(instanceId,commandName,map,true,type);
      if (stats != null) {
        resultList.add(stats);
      }
    }
  }
  return resultList;
}
