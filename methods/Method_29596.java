@RequestMapping(value="/addAuditStatus") public ModelAndView doAddAuditStatus(HttpServletRequest request,HttpServletResponse response,Model model,Integer status,Long appAuditId,String refuseReason){
  AppAudit appAudit=appService.getAppAuditById(appAuditId);
  AppUser appUser=userService.get(appAudit.getUserId());
  appService.updateUserAuditStatus(appAuditId,status);
  if (AppCheckEnum.APP_REJECT.value().equals(status)) {
    appAudit.setRefuseReason(refuseReason);
    appService.updateRefuseReason(appAudit,getUserInfo(request));
    userService.delete(appUser.getId());
  }
  if (AppCheckEnum.APP_PASS.value().equals(status) || AppCheckEnum.APP_REJECT.value().equals(status)) {
    appUser.setType(AppUserTypeEnum.REGULAR_USER.value());
    appAudit.setStatus(status);
    userService.update(appUser);
    appEmailUtil.noticeUserResult(appUser,appAudit);
  }
  if (AppCheckEnum.APP_PASS.value().equals(status)) {
    return new ModelAndView("redirect:/manage/app/auditList");
  }
  write(response,String.valueOf(SuccessEnum.SUCCESS.value()));
  return null;
}
