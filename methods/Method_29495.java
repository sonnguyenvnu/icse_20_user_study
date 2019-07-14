/** 
 * ????????????????????
 * @param appId ????
 */
@RequestMapping("/getAppInstancesNetStat") public ModelAndView getAppInstancesNetStat(HttpServletRequest request,HttpServletResponse response,Model model,Long appId) throws ParseException {
  TimeBetween timeBetween=getJsonTimeBetween(request);
  String netInCommand="total_net_input_bytes";
  String netOutCommand="total_net_output_bytes";
  Map<String,String> commandMap=new HashMap<String,String>();
  commandMap.put(netInCommand,"i");
  commandMap.put(netOutCommand,"o");
  Map<Integer,Map<String,List<InstanceCommandStats>>> appInstancesNetStat=instanceStatsCenter.getStandardStatsList(appId,timeBetween.getStartTime(),timeBetween.getEndTime(),Arrays.asList(netInCommand,netOutCommand));
  List<Map<String,Object>> appInstancesNetStatList=new ArrayList<Map<String,Object>>();
  for (  Entry<Integer,Map<String,List<InstanceCommandStats>>> entry : appInstancesNetStat.entrySet()) {
    Integer instanceId=entry.getKey();
    Map<String,Object> instanceStatMap=new HashMap<String,Object>();
    instanceStatMap.put("instanceId",instanceId);
    InstanceInfo instanceInfo=instanceStatsCenter.getInstanceInfo(instanceId);
    instanceStatMap.put("instanceInfo",instanceInfo.getIp() + ":" + instanceInfo.getPort());
    List<Map<String,Object>> instanceNetStatMapList=new ArrayList<Map<String,Object>>();
    instanceStatMap.put("instanceNetStatMapList",instanceNetStatMapList);
    appInstancesNetStatList.add(instanceStatMap);
    Map<String,List<InstanceCommandStats>> map=entry.getValue();
    List<InstanceCommandStats> instanceCommandStatsList=new ArrayList<InstanceCommandStats>();
    instanceCommandStatsList.addAll(map.get(netInCommand));
    instanceCommandStatsList.addAll(map.get(netOutCommand));
    Map<Long,Map<String,Object>> total=new HashMap<Long,Map<String,Object>>();
    for (    InstanceCommandStats instanceCommandStat : instanceCommandStatsList) {
      long timestamp=instanceCommandStat.getTimeStamp();
      long commandCount=instanceCommandStat.getCommandCount();
      String command=instanceCommandStat.getCommandName();
      command=commandMap.get(command);
      if (total.containsKey(timestamp)) {
        Map<String,Object> tmpMap=total.get(timestamp);
        tmpMap.put(command,commandCount);
      }
 else {
        Map<String,Object> tmpMap=new HashMap<String,Object>();
        tmpMap.put("t",timestamp);
        tmpMap.put(command,commandCount);
        total.put(timestamp,tmpMap);
        instanceNetStatMapList.add(tmpMap);
      }
    }
  }
  String result=JSONObject.toJSONString(appInstancesNetStatList);
  write(response,result);
  return null;
}
