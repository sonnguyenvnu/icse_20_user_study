@RequestMapping(value="/demo") public ModelAndView doDemo(HttpServletRequest request,HttpServletResponse response,Long appId,Model model){
  if (appId != null && appId > 0) {
    AppDesc appDesc=appService.getByAppId(appId);
    List<String> code=DemoCodeUtil.getCode(appDesc.getType(),appDesc.getAppId());
    List<String> dependency=DemoCodeUtil.getDependencyRedis();
    List<String> springConfig=DemoCodeUtil.getSpringConfig(appDesc.getType(),appDesc.getAppId());
    String restApi=DemoCodeUtil.getRestAPI(appDesc.getType(),appDesc.getAppId());
    if (CollectionUtils.isNotEmpty(springConfig) && springConfig.size() > 0) {
      model.addAttribute("springConfig",springConfig);
    }
    model.addAttribute("dependency",dependency);
    model.addAttribute("code",code);
    model.addAttribute("status",1);
    model.addAttribute("restApi",restApi);
  }
 else {
    model.addAttribute("status",0);
  }
  return new ModelAndView("app/appDemo");
}
