/** 
 * ?????????????
 * @param appId
 */
@RequestMapping("/detail") public ModelAndView appInfoAndAudit(HttpServletRequest request,HttpServletResponse response,Model model,Long appId){
  if (appId != null && appId > 0) {
    List<AppAudit> appAuditList=appService.getAppAuditListByAppId(appId);
    AppDesc appDesc=appService.getByAppId(appId);
    model.addAttribute("appAuditList",appAuditList);
    model.addAttribute("appDesc",appDesc);
  }
  return new ModelAndView("manage/appOps/appInfoAndAudit");
}
