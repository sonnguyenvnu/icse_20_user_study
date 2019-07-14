/** 
 * ?????????
 */
@RequestMapping(value="/initHorizontalScaleApply") public ModelAndView doInitHorizontalScaleApply(HttpServletRequest request,HttpServletResponse response,Model model,Long appAuditId){
  AppAudit appAudit=appService.getAppAuditById(appAuditId);
  model.addAttribute("appAudit",appAudit);
  model.addAttribute("appId",appAudit.getAppId());
  return new ModelAndView("manage/appAudit/initHorizontalScaleApply");
}
