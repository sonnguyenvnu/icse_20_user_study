/** 
 * ???????????
 * @param appId ??id
 * @throws ParseException
 */
@RequestMapping("/getCommandStats") public ModelAndView getCommandStats(HttpServletRequest request,HttpServletResponse response,Model model,Long appId) throws ParseException {
  TimeBetween timeBetween=getJsonTimeBetween(request);
  long beginTime=timeBetween.getStartTime();
  long endTime=timeBetween.getEndTime();
  String commandName=request.getParameter("commandName");
  List<AppCommandStats> appCommandStatsList;
  if (StringUtils.isNotBlank(commandName)) {
    appCommandStatsList=appStatsCenter.getCommandStatsList(appId,beginTime,endTime,commandName);
  }
 else {
    appCommandStatsList=appStatsCenter.getCommandStatsList(appId,beginTime,endTime);
  }
  String result=assembleJson(appCommandStatsList);
  write(response,result);
  return null;
}
