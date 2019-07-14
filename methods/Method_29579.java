/** 
 * @param appId ??id
 * @param host ??ip
 * @param port ????
 * @param instanceConfigKey ????key
 * @param instanceConfigValue ????value
 * @param appAuditId ??id
 * @return
 */
@RequestMapping(value="/addInstanceConfigChange") public ModelAndView doAddAppConfigChange(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,String host,int port,String instanceConfigKey,String instanceConfigValue,Long appAuditId){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} change instanceConfig:appId={},{}:{};key={};value={},appAuditId:{}",appUser.getName(),appId,host,port,instanceConfigKey,instanceConfigValue,appAuditId);
  boolean isModify=false;
  if (StringUtils.isNotBlank(host) && port > 0 && appAuditId != null && StringUtils.isNotBlank(instanceConfigKey) && StringUtils.isNotBlank(instanceConfigValue)) {
    try {
      isModify=instanceDeployCenter.modifyInstanceConfig(appId,appAuditId,host,port,instanceConfigKey,instanceConfigValue);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
  logger.warn("user {} change instanceConfig:appId={},{}:{};key={};value={},appAuditId:{},result is:{}",appUser.getName(),appId,host,port,instanceConfigKey,instanceConfigValue,appAuditId,isModify);
  return new ModelAndView("redirect:/manage/app/auditList");
}
