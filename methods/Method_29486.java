/** 
 * ????????????????????????
 * @param appId
 */
@RequestMapping("/getAppClientInstanceCommandCost") public ModelAndView doGetAppClientInstanceCommandCost(HttpServletRequest request,HttpServletResponse response,Model model) throws ParseException {
  final String costDistriDateFormat="yyyy-MM-dd";
  long appId=NumberUtils.toLong(request.getParameter("appId"));
  String costDistriStartDate=request.getParameter("costDistriStartDate");
  String costDistriEndDate=request.getParameter("costDistriEndDate");
  Date startDate=DateUtil.parse(costDistriStartDate,costDistriDateFormat);
  Date endDate=DateUtil.parse(costDistriEndDate,costDistriDateFormat);
  long startTime=NumberUtils.toLong(DateUtil.formatDate(startDate,COLLECT_TIME_FORMAT));
  long endTime=NumberUtils.toLong(DateUtil.formatDate(endDate,COLLECT_TIME_FORMAT));
  String firstCommand=request.getParameter("firstCommand");
  long instanceId=NumberUtils.toLong(request.getParameter("instanceId"));
  String clientIp=request.getParameter("clientIp");
  List<AppClientCostTimeStat> clientInstanceChartStatList=clientReportCostDistriService.getAppCommandClientToInstanceStat(appId,firstCommand,instanceId,clientIp,startTime,endTime);
  List<Map<String,Object>> clientInstanceStat=new ArrayList<Map<String,Object>>();
  for (  AppClientCostTimeStat appClientCostTimeStat : clientInstanceChartStatList) {
    Map<String,Object> map=new HashMap<String,Object>();
    map.put("timeStamp",appClientCostTimeStat.getTimeStamp());
    map.put("count",appClientCostTimeStat.getCount());
    map.put("mean",appClientCostTimeStat.getMean());
    map.put("median",appClientCostTimeStat.getMedian());
    map.put("max90",appClientCostTimeStat.getNinetyPercentMax());
    map.put("max99",appClientCostTimeStat.getNinetyNinePercentMax());
    map.put("max100",appClientCostTimeStat.getHundredMax());
    clientInstanceStat.add(map);
  }
  Map<String,Object> resultMap=new HashMap<String,Object>();
  resultMap.put("clientInstanceStat",clientInstanceStat);
  sendMessage(response,JSONObject.toJSONString(resultMap));
  return null;
}
