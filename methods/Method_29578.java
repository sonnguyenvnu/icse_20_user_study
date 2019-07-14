/** 
 * ??redis????
 * @param appId
 * @param slaveInstanceId
 */
@RequestMapping("/log") public ModelAndView doShowLog(HttpServletRequest request,HttpServletResponse response,Model model,int instanceId){
  int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),0);
  if (pageSize == 0) {
    pageSize=100;
  }
  String instanceLogStr=instanceDeployCenter.showInstanceRecentLog(instanceId,pageSize);
  model.addAttribute("instanceLogList",StringUtils.isBlank(instanceLogStr) ? Collections.emptyList() : Arrays.asList(instanceLogStr.split("\n")));
  return new ModelAndView("manage/instance/log");
}
