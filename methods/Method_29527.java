/** 
 * ????????
 * @param appId ??id
 * @param appConfigKey ???
 * @param appConfigValue ???
 * @param appAuditId ??id
 */
@RequestMapping(value="/addAppConfigChange") public ModelAndView doAddAppConfigChange(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,String appConfigKey,String appConfigValue,Long appAuditId){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} change appConfig:appId={};key={};value={},appAuditId:{}",appUser.getName(),appId,appConfigKey,appConfigValue,appAuditId);
  boolean isModify=false;
  if (appId != null && appAuditId != null && StringUtils.isNotBlank(appConfigKey) && StringUtils.isNotBlank(appConfigValue)) {
    try {
      isModify=appDeployCenter.modifyAppConfig(appId,appAuditId,appConfigKey,appConfigValue);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
  logger.warn("user {} change appConfig:appId={};key={};value={},appAuditId:{},result is:{}",appUser.getName(),appId,appConfigKey,appConfigValue,appAuditId,isModify);
  return new ModelAndView("redirect:/manage/app/auditList");
}
