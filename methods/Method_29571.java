@RequestMapping("/index") public ModelAndView index(HttpServletRequest request,HttpServletResponse response,Model model,Integer admin,Long instanceId,Long appId,String tabTag){
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
    if (instanceInfo == null) {
      model.addAttribute("type",-1);
    }
 else {
      if (appId != null && appId > 0) {
        model.addAttribute("appId",appId);
      }
 else {
        model.addAttribute("appId",instanceInfo.getAppId());
      }
      model.addAttribute("type",instanceInfo.getType());
    }
  }
 else {
  }
  if (tabTag != null) {
    model.addAttribute("tabTag",tabTag);
  }
  return new ModelAndView("instance/instanceIndex");
}
