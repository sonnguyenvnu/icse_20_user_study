/** 
 * ???????
 * @param appId
 * @return
 * @throws ParseException 
 */
@RequestMapping("/slowLog") public ModelAndView appSlowLog(HttpServletRequest request,HttpServletResponse response,Model model,Long appId) throws ParseException {
  AppDesc appDesc=appService.getByAppId(appId);
  model.addAttribute("appDesc",appDesc);
  TimeBetween timeBetween=getTimeBetween(request,model,"slowLogStartDate","slowLogEndDate");
  Date startDate=timeBetween.getStartDate();
  Date endDate=timeBetween.getEndDate();
  Map<String,Long> appInstanceSlowLogCountMap=appStatsCenter.getInstanceSlowLogCountMapByAppId(appId,startDate,endDate);
  model.addAttribute("appInstanceSlowLogCountMap",appInstanceSlowLogCountMap);
  List<InstanceSlowLog> appInstanceSlowLogList=appStatsCenter.getInstanceSlowLogByAppId(appId,startDate,endDate);
  model.addAttribute("appInstanceSlowLogList",appInstanceSlowLogList);
  Map<String,List<InstanceSlowLog>> instaceSlowLogMap=new HashMap<String,List<InstanceSlowLog>>();
  Map<String,Long> instanceHostPortIdMap=new HashMap<String,Long>();
  for (  InstanceSlowLog instanceSlowLog : appInstanceSlowLogList) {
    String hostPort=instanceSlowLog.getIp() + ":" + instanceSlowLog.getPort();
    instanceHostPortIdMap.put(hostPort,instanceSlowLog.getInstanceId());
    if (instaceSlowLogMap.containsKey(hostPort)) {
      instaceSlowLogMap.get(hostPort).add(instanceSlowLog);
    }
 else {
      List<InstanceSlowLog> list=new ArrayList<InstanceSlowLog>();
      list.add(instanceSlowLog);
      instaceSlowLogMap.put(hostPort,list);
    }
  }
  model.addAttribute("instaceSlowLogMap",instaceSlowLogMap);
  model.addAttribute("instanceHostPortIdMap",instanceHostPortIdMap);
  return new ModelAndView("app/slowLog");
}
