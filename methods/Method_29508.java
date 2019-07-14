/** 
 * ??????
 */
@RequestMapping("/daily") public ModelAndView appDaily(HttpServletRequest request,HttpServletResponse response,Model model,Long appId) throws ParseException {
  AppDesc appDesc=appService.getByAppId(appId);
  model.addAttribute("appDesc",appDesc);
  String dailyDateParam=request.getParameter("dailyDate");
  Date date;
  if (StringUtils.isBlank(dailyDateParam)) {
    date=DateUtils.addDays(new Date(),-1);
  }
 else {
    date=DateUtil.parseYYYY_MM_dd(dailyDateParam);
  }
  model.addAttribute("dailyDate",dailyDateParam);
  AppDailyData appDailyData=appDailyDataCenter.getAppDailyData(appId,date);
  model.addAttribute("appDailyData",appDailyData);
  return new ModelAndView("app/appDaily");
}
