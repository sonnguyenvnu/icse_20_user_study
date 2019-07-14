/** 
 * ??????
 */
@RequestMapping("/stat") public ModelAndView appStat(HttpServletRequest request,HttpServletResponse response,Model model,Long appId) throws ParseException {
  AppDetailVO appDetail=appStatsCenter.getAppDetail(appId);
  model.addAttribute("appDetail",appDetail);
  TimeBetween timeBetween=getTimeBetween(request,model,"startDate","endDate");
  long beginTime=timeBetween.getStartTime();
  long endTime=timeBetween.getEndTime();
  if (endTime - beginTime > TimeUnit.DAYS.toMillis(1)) {
    model.addAttribute("betweenOneDay",0);
  }
 else {
    model.addAttribute("betweenOneDay",1);
  }
  List<AppCommandStats> top5Commands=appStatsCenter.getTopLimitAppCommandStatsList(appId,beginTime,endTime,5);
  model.addAttribute("top5Commands",top5Commands);
  List<AppCommandStats> top5ClimaxList=new ArrayList<AppCommandStats>();
  if (CollectionUtils.isNotEmpty(top5Commands)) {
    for (    AppCommandStats appCommandStats : top5Commands) {
      AppCommandStats temp=appStatsCenter.getCommandClimax(appId,beginTime,endTime,appCommandStats.getCommandName());
      if (temp != null) {
        top5ClimaxList.add(temp);
      }
    }
  }
  model.addAttribute("top5ClimaxList",top5ClimaxList);
  model.addAttribute("appId",appId);
  return new ModelAndView("app/appStat");
}
