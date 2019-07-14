/** 
 * ??????????
 * @param appId ??id
 * @throws ParseException
 */
@RequestMapping("/appCommandDistribute") public ModelAndView appCommandDistribute(HttpServletRequest request,HttpServletResponse response,Model model,Long appId) throws ParseException {
  TimeBetween timeBetween=getJsonTimeBetween(request);
  List<AppCommandGroup> appCommandGroupList=appStatsCenter.getAppCommandGroup(appId,timeBetween.getStartTime(),timeBetween.getEndTime());
  String result=assembleGroupJson(appCommandGroupList);
  write(response,result);
  return null;
}
