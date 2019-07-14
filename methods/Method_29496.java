/** 
 * @param appId
 * @throws ParseException
 */
@RequestMapping("/getTop5Commands") public ModelAndView getAppTop5Commands(HttpServletRequest request,HttpServletResponse response,Model model,Long appId) throws ParseException {
  TimeBetween timeBetween=getJsonTimeBetween(request);
  List<AppCommandStats> appCommandStats=appStatsCenter.getTop5AppCommandStatsList(appId,timeBetween.getStartTime(),timeBetween.getEndTime());
  String result=assembleJson(appCommandStats);
  write(response,result);
  return null;
}
