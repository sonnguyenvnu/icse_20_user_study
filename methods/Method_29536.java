/** 
 * ???????
 * @param appAuditId ??id
 * @return
 */
@RequestMapping(value="/initAppDeploy") public ModelAndView doInitAppDeploy(HttpServletRequest request,HttpServletResponse response,Model model,Long appAuditId){
  AppAudit appAudit=appService.getAppAuditById(appAuditId);
  model.addAttribute("appAudit",appAudit);
  List<MachineStats> machineList=machineCenter.getAllMachineStats();
  model.addAttribute("machineList",machineList);
  model.addAttribute("appAuditId",appAuditId);
  model.addAttribute("appId",appAudit.getAppId());
  model.addAttribute("appDesc",appService.getByAppId(appAudit.getAppId()));
  return new ModelAndView("manage/appAudit/initAppDeploy");
}
