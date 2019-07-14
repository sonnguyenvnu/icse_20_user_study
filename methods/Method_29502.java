/** 
 * ????
 * @param appId          ??id
 * @param applyMemSize   ????
 * @param appScaleReason ????
 * @return
 */
@RequestMapping(value="/scale") public ModelAndView doScaleApp(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,String applyMemSize,String appScaleReason){
  AppUser appUser=getUserInfo(request);
  AppDesc appDesc=appService.getByAppId(appId);
  AppAudit appAudit=appService.saveAppScaleApply(appDesc,appUser,applyMemSize,appScaleReason,AppAuditType.APP_SCALE);
  appEmailUtil.noticeAppResult(appDesc,appAudit);
  write(response,String.valueOf(SuccessEnum.SUCCESS.value()));
  return null;
}
