/** 
 * /manage/client/version
 * @param request
 * @param response
 * @param model
 * @return
 */
@RequestMapping(value="/version") public ModelAndView doVersionStat(HttpServletRequest request,HttpServletResponse response,Model model){
  long appId=NumberUtils.toLong(request.getParameter("appId"),-1);
  List<AppClientVersion> appClientVersionList=clientVersionService.getAll(appId);
  fillAppInfoMap(model);
  model.addAttribute("appClientVersionList",appClientVersionList);
  model.addAttribute("clientVersionActive",SuccessEnum.SUCCESS.value());
  model.addAttribute("appId",request.getParameter("appId"));
  return new ModelAndView("manage/client/version/list");
}
