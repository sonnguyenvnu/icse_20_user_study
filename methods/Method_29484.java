/** 
 * ?????????
 */
@RequestMapping("/costDistribute") public ModelAndView doCostDistribute(HttpServletRequest request,HttpServletResponse response,Model model){
  Long appId=NumberUtils.toLong(request.getParameter("appId"));
  if (appId <= 0) {
    return new ModelAndView("");
  }
  AppDesc appDesc=appService.getByAppId(appId);
  model.addAttribute("appDesc",appDesc);
  model.addAttribute("appId",appId);
  TimeBetween timeBetween=new TimeBetween();
  try {
    timeBetween=fillWithCostDateFormat(request,model);
  }
 catch (  ParseException e) {
    logger.error(e.getMessage(),e);
  }
  long startTime=timeBetween.getStartTime();
  long endTime=timeBetween.getEndTime();
  Date startDate=timeBetween.getStartDate();
  List<String> allCommands=clientReportCostDistriService.getAppDistinctCommand(appId,startTime,endTime);
  model.addAttribute("allCommands",allCommands);
  List<AppInstanceClientRelation> appInstanceClientRelationList=appInstanceClientRelationService.getAppInstanceClientRelationList(appId,startDate);
  model.addAttribute("appInstanceClientRelationList",appInstanceClientRelationList);
  String firstCommand=request.getParameter("firstCommand");
  if (StringUtils.isBlank(firstCommand) && CollectionUtils.isNotEmpty(allCommands)) {
    firstCommand=allCommands.get(0);
    model.addAttribute("firstCommand",firstCommand);
  }
 else {
    model.addAttribute("firstCommand",firstCommand);
  }
  List<AppClientCostTimeTotalStat> appChartStatList=clientReportCostDistriService.getAppClientCommandTotalStat(appId,firstCommand,startTime,endTime);
  Map<String,Object> resultMap=new HashMap<String,Object>();
  List<Map<String,Object>> app=new ArrayList<Map<String,Object>>();
  for (  AppClientCostTimeTotalStat appClientCostTimeTotalStat : appChartStatList) {
    Map<String,Object> map=new HashMap<String,Object>();
    map.put("timeStamp",appClientCostTimeTotalStat.getTimeStamp());
    map.put("count",appClientCostTimeTotalStat.getTotalCount());
    map.put("mean",appClientCostTimeTotalStat.getMean());
    map.put("median",appClientCostTimeTotalStat.getMedian());
    map.put("max90",appClientCostTimeTotalStat.getNinetyPercentMax());
    map.put("max99",appClientCostTimeTotalStat.getNinetyNinePercentMax());
    map.put("max100",appClientCostTimeTotalStat.getHundredMax());
    map.put("maxInst",appClientCostTimeTotalStat.getMaxInstanceHost() + ":" + appClientCostTimeTotalStat.getMaxInstancePort());
    map.put("maxClient",appClientCostTimeTotalStat.getMaxClientIp());
    app.add(map);
  }
  resultMap.put("app",app);
  model.addAttribute("appChartStatListJson",JSONObject.toJSONString(resultMap));
  return new ModelAndView("client/clientCostDistribute");
}
