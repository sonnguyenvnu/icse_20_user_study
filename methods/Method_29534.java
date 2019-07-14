/** 
 * ??????
 * @param appAuditId ??id
 */
@RequestMapping(value="/initAppScaleApply") public ModelAndView doInitAppScaleApply(HttpServletRequest request,HttpServletResponse response,Model model,Long appAuditId){
  AppAudit appAudit=appService.getAppAuditById(appAuditId);
  model.addAttribute("appAudit",appAudit);
  fillAppInstanceStats(appAudit.getAppId(),model);
  fillAppMachineStat(appAudit.getAppId(),model);
  long appId=appAudit.getAppId();
  AppDesc appDesc=appService.getByAppId(appId);
  model.addAttribute("appAuditId",appAuditId);
  model.addAttribute("appId",appAudit.getAppId());
  model.addAttribute("appDesc",appDesc);
  return new ModelAndView("manage/appAudit/initAppScaleApply");
}
