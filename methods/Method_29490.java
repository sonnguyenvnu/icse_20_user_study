/** 
 * ????
 * @param firstCommand ?????
 */
@RequestMapping("/commandAnalysis") public ModelAndView appCommandAnalysis(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,String firstCommand) throws ParseException {
  AppDetailVO appDetail=appStatsCenter.getAppDetail(appId);
  model.addAttribute("appDetail",appDetail);
  TimeBetween timeBetween=getTimeBetween(request,model,"startDate","endDate");
  if (timeBetween.getEndTime() - timeBetween.getStartTime() > TimeUnit.DAYS.toMillis(1)) {
    model.addAttribute("betweenOneDay",0);
  }
 else {
    model.addAttribute("betweenOneDay",1);
  }
  List<AppCommandStats> allCommands=appStatsCenter.getTopLimitAppCommandStatsList(appId,timeBetween.getStartTime(),timeBetween.getEndTime(),20);
  model.addAttribute("allCommands",allCommands);
  if (StringUtils.isBlank(firstCommand) && CollectionUtils.isNotEmpty(allCommands)) {
    model.addAttribute("firstCommand",allCommands.get(0).getCommandName());
  }
 else {
    model.addAttribute("firstCommand",firstCommand);
  }
  model.addAttribute("appId",appId);
  return new ModelAndView("app/appCommandAnalysis");
}
