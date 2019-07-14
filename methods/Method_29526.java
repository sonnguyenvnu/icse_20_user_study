/** 
 * ????????
 * @param appAuditId ??id
 */
@RequestMapping(value="/initAppConfigChange") public ModelAndView doInitAppConfigChange(HttpServletRequest request,HttpServletResponse response,Model model,Long appAuditId){
  AppAudit appAudit=appService.getAppAuditById(appAuditId);
  model.addAttribute("appAudit",appAudit);
  Long instanceId=NumberUtils.toLong(appAudit.getParam1());
  Map<String,String> redisConfigList=redisCenter.getRedisConfigList(instanceId.intValue());
  model.addAttribute("redisConfigList",redisConfigList);
  model.addAttribute("instanceId",instanceId);
  List<InstanceInfo> instanceList=appService.getAppInstanceInfo(appAudit.getAppId());
  model.addAttribute("instanceList",instanceList);
  model.addAttribute("appId",appAudit.getAppId());
  model.addAttribute("appAuditId",appAuditId);
  model.addAttribute("appConfigKey",appAudit.getParam2());
  model.addAttribute("appConfigValue",appAudit.getParam3());
  return new ModelAndView("manage/appAudit/initAppConfigChange");
}
