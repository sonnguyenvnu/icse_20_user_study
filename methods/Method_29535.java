/** 
 * ??????
 * @param appScaleText ????
 * @param appAuditId ??id
 */
@RequestMapping(value="/addAppScaleApply") public ModelAndView doAddAppScaleApply(HttpServletRequest request,HttpServletResponse response,Model model,String appScaleText,Long appAuditId,Long appId){
  AppUser appUser=getUserInfo(request);
  logger.error("user {} appScaleApplay : appScaleText={},appAuditId:{}",appUser.getName(),appScaleText,appAuditId);
  boolean isSuccess=false;
  if (appAuditId != null && StringUtils.isNotBlank(appScaleText)) {
    int mem=NumberUtils.toInt(appScaleText,0);
    try {
      isSuccess=appDeployCenter.verticalExpansion(appId,appAuditId,mem);
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
 else {
    logger.error("appScaleApplay error param: appScaleText={},appAuditId:{}",appScaleText,appAuditId);
  }
  logger.error("user {} appScaleApplay: appScaleText={},appAuditId:{}, result is {}",appUser.getName(),appScaleText,appAuditId,isSuccess);
  return new ModelAndView("redirect:/manage/app/auditList");
}
