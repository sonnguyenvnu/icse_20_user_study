@RequestMapping("/advancedAnalysis") public ModelAndView advancedAnalysis(HttpServletRequest request,HttpServletResponse response,Model model,Integer admin,Long instanceId){
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
  if (instanceId != null && instanceId > 0) {
    model.addAttribute("instanceId",instanceId);
    InstanceInfo instanceInfo=instanceStatsCenter.getInstanceInfo(instanceId);
    model.addAttribute("instanceInfo",instanceInfo);
    model.addAttribute("appId",instanceInfo.getAppId());
    List<AppCommandStats> topLimitAppCommandStatsList=appStatsCenter.getTopLimitAppCommandStatsList(instanceInfo.getAppId(),Long.parseLong(startDateParam) * 10000,Long.parseLong(endDateParam) * 10000,5);
    model.addAttribute("appCommandStats",topLimitAppCommandStatsList);
  }
 else {
  }
  return new ModelAndView("instance/instanceAdvancedAnalysis");
}
