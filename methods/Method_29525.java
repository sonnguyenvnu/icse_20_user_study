@RequestMapping("/appDaily") public ModelAndView appDaily(HttpServletRequest request,HttpServletResponse response,Model model) throws ParseException {
  AppUser userInfo=getUserInfo(request);
  logger.warn("user {} want to send appdaily",userInfo.getName());
  if (ConstUtils.SUPER_MANAGER.contains(userInfo.getName())) {
    Date startDate;
    Date endDate;
    String startDateParam=request.getParameter("startDate");
    String endDateParam=request.getParameter("endDate");
    if (StringUtils.isBlank(startDateParam) || StringUtils.isBlank(endDateParam)) {
      endDate=new Date();
      startDate=DateUtils.addDays(endDate,-1);
    }
 else {
      startDate=DateUtil.parseYYYY_MM_dd(startDateParam);
      endDate=DateUtil.parseYYYY_MM_dd(endDateParam);
    }
    long appId=NumberUtils.toLong(request.getParameter("appId"));
    if (appId > 0) {
      appDailyDataCenter.sendAppDailyEmail(appId,startDate,endDate);
    }
 else {
      appDailyDataCenter.sendAppDailyEmail();
    }
    model.addAttribute("msg","success!");
  }
 else {
    model.addAttribute("msg","no power!");
  }
  return new ModelAndView("");
}
