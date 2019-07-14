/** 
 * ???????????
 * @param instanceId  ??id
 * @param commandName ????
 * @throws java.text.ParseException
 */
@RequestMapping("/getCommandStatsV2") public ModelAndView getCommandStatsV2(HttpServletRequest request,HttpServletResponse response,Model model,Long instanceId,String commandName) throws ParseException {
  String startDateParam=request.getParameter("startDate");
  String endDateParam=request.getParameter("endDate");
  if (StringUtils.isBlank(startDateParam) || StringUtils.isBlank(endDateParam)) {
    Date endDate=new Date();
    Date startDate=DateUtils.addDays(endDate,-1);
    startDateParam=DateUtil.formatDate(startDate,"yyyyMMdd");
    endDateParam=DateUtil.formatDate(endDate,"yyyyMMdd");
  }
  model.addAttribute("startDate",startDateParam);
  model.addAttribute("endDate",endDateParam);
  Date startDate=DateUtil.parseYYYYMMdd(startDateParam);
  Date endDate=DateUtil.parseYYYYMMdd(endDateParam);
  if (instanceId != null) {
    long firstDayBegin=NumberUtils.toLong(DateUtil.formatYYYYMMdd(startDate) + "0000");
    long firstDayEnd=NumberUtils.toLong(DateUtil.formatYYYYMMdd(startDate) + "2359");
    long secondDayBegin=NumberUtils.toLong(DateUtil.formatYYYYMMdd(endDate) + "0000");
    long secondDayEnd=NumberUtils.toLong(DateUtil.formatYYYYMMdd(endDate) + "2359");
    long bt=System.currentTimeMillis();
    List<InstanceCommandStats> instanceCommandStatsListFirst=instanceStatsCenter.getCommandStatsList(instanceId,firstDayBegin,firstDayEnd,commandName);
    List<InstanceCommandStats> instanceCommandStatsListSecond=instanceStatsCenter.getCommandStatsList(instanceId,secondDayBegin,secondDayEnd,commandName);
    long et=System.currentTimeMillis() - bt;
    Map<String,InstanceCommandStats> cmdStatsFirst=new HashMap<String,InstanceCommandStats>();
    Map<String,InstanceCommandStats> cmdStatsSecond=new HashMap<String,InstanceCommandStats>();
    for (    InstanceCommandStats first : instanceCommandStatsListFirst) {
      cmdStatsFirst.put(first.getCollectTime() + "",first);
    }
    for (    InstanceCommandStats second : instanceCommandStatsListSecond) {
      cmdStatsSecond.put(second.getCollectTime() + "",second);
    }
    SplineChartEntity splineChartEntity=new SplineChartEntity();
    String container=request.getParameter("container");
    if (container != null) {
      splineChartEntity.renderTo(container);
    }
    model.addAttribute("chart",splineChartEntity);
    splineChartEntity.putTitle(ChartKeysUtil.TitleKey.TEXT.getKey(),"??:" + commandName + " ??????" + startDateParam + "?-?" + endDateParam + "?");
    splineChartEntity.setYAxisTitle("y");
    List<Long> data1=new ArrayList<Long>();
    List<Long> data2=new ArrayList<Long>();
    Map<String,Object> marker=new HashMap<String,Object>();
    marker.put("radius",1);
    Map<String,Object> serie1=new HashMap<String,Object>();
    serie1.put("name",startDateParam);
    serie1.put("data",data1);
    serie1.put("marker",marker);
    Map<String,Object> serie2=new HashMap<String,Object>();
    serie2.put("name",endDateParam);
    serie2.put("data",data2);
    serie2.put("marker",marker);
    splineChartEntity.putSeries(serie1);
    splineChartEntity.putSeries(serie2);
    List<Object> x=new LinkedList<Object>();
    for (int i=0; i < 1440; i+=1) {
      Date date=DateUtils.addMinutes(startDate,i);
      String s=DateUtil.formatHHMM(date);
      if (cmdStatsFirst.containsKey(startDateParam + s)) {
        data1.add(cmdStatsFirst.get(startDateParam + s).getCommandCount());
      }
 else {
        data1.add(0l);
      }
      if (cmdStatsSecond.containsKey(endDateParam + s)) {
        data2.add(cmdStatsSecond.get(endDateParam + s).getCommandCount());
      }
 else {
        data2.add(0l);
      }
      x.add(s);
    }
    splineChartEntity.setXAxisCategories(x);
  }
  return new ModelAndView("");
}
