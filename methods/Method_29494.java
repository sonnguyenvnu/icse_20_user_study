/** 
 * ????????????
 * @param appId    ??id
 * @param statName ???(hit,miss?)
 * @throws ParseException
 */
@RequestMapping("/getMutiDatesAppStats") public ModelAndView getMutiDatesAppStats(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,String statName,Integer addDay) throws ParseException {
  TimeBetween timeBetween=getJsonTimeBetween(request);
  List<AppStats> appStats=appStatsCenter.getAppStatsList(appId,timeBetween.getStartTime(),timeBetween.getEndTime(),TimeDimensionalityEnum.MINUTE);
  String result=assembleMutilDateAppStatsJsonMinute(appStats,statName,timeBetween.getStartDate(),timeBetween.getEndDate());
  model.addAttribute("data",result);
  return new ModelAndView("");
}
