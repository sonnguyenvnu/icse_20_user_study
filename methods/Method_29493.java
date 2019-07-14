/** 
 * ????????????
 * @param appId    ??id
 * @param statName ???(hit,miss?)
 * @throws ParseException
 */
@RequestMapping("/getAppStats") public ModelAndView getAppStats(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,String statName) throws ParseException {
  TimeBetween timeBetween=getJsonTimeBetween(request);
  List<AppStats> appStats=appStatsCenter.getAppStatsListByMinuteTime(appId,timeBetween.getStartTime(),timeBetween.getEndTime());
  String result=assembleAppStatsJson(appStats,statName);
  write(response,result);
  return null;
}
