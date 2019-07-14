@RequestMapping(value="/add") public ModelAndView add(HttpServletRequest request,HttpServletResponse response,Model model){
  AppDesc appDesc=genAppDesc(request);
  String appInstanceInfo=request.getParameter("appInstanceInfo");
  logger.warn("appDesc:" + appDesc);
  logger.warn("appInstanceInfo: " + appInstanceInfo);
  boolean isSuccess=importAppCenter.importAppAndInstance(appDesc,appInstanceInfo);
  logger.warn("import app result is {}",isSuccess);
  model.addAttribute("status",isSuccess ? 1 : 0);
  return new ModelAndView("");
}
