/** 
 * ??,??????
 * @param status ????
 * @param appAuditId ??id
 * @param refuseReason ??id
 * @return
 */
@RequestMapping(value="/addAuditStatus") public ModelAndView doAddAuditStatus(HttpServletRequest request,HttpServletResponse response,Model model,Integer status,Long appAuditId,String refuseReason){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} addAuditStatus: status={},appAuditId:{},refuseReason:{}",appUser.getName(),status,appAuditId,refuseReason);
  AppAudit appAudit=appService.getAppAuditById(appAuditId);
  Long appId=appAudit.getAppId();
  appService.updateAppAuditStatus(appAuditId,appId,status,getUserInfo(request));
  if (AppCheckEnum.APP_REJECT.value().equals(status)) {
    appAudit.setRefuseReason(refuseReason);
    appService.updateRefuseReason(appAudit,getUserInfo(request));
  }
  if (AppCheckEnum.APP_PASS.value().equals(status) || AppCheckEnum.APP_REJECT.value().equals(status)) {
    AppDesc appDesc=appService.getByAppId(appId);
    appEmailUtil.noticeAppResult(appDesc,appService.getAppAuditById(appAuditId));
  }
  if (AppCheckEnum.APP_PASS.value().equals(status)) {
    return new ModelAndView("redirect:/manage/app/auditList");
  }
  write(response,String.valueOf(SuccessEnum.SUCCESS.value()));
  return null;
}
