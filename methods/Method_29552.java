/** 
 * ??????app?????
 * @param request
 * @param appId
 * @return
 */
protected boolean checkAppUserProvilege(HttpServletRequest request,long appId){
  AppUser currentUser=getUserInfo(request);
  if (currentUser == null) {
    logger.error("currentUser is empty");
    return false;
  }
  if (AppUserTypeEnum.ADMIN_USER.value().equals(currentUser.getType())) {
    return true;
  }
  List<AppToUser> appToUsers=appService.getAppToUserList(appId);
  if (CollectionUtils.isEmpty(appToUsers)) {
    logger.error("appId {} userList is empty",appId);
    return false;
  }
  Set<Long> appUserIdSet=new HashSet<Long>();
  for (  AppToUser appToUser : appToUsers) {
    appUserIdSet.add(appToUser.getUserId());
  }
  if (!appUserIdSet.contains(currentUser.getId())) {
    logger.error("currentUser {} hasn't previlege in appId {}",currentUser.getId(),appId);
    return false;
  }
  return true;
}
