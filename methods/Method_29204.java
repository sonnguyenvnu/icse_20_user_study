/** 
 * ?????????
 * @param response
 * @param session
 * @param user
 * @param appId
 * @return
 */
private void checkUserAppPower(HttpServletResponse response,HttpSession session,AppUser user,Long appId){
  List<AppToUser> appToUsers=appService.getAppToUserList(appId);
  if (CollectionUtils.isNotEmpty(appToUsers)) {
    for (    AppToUser tempAppToUser : appToUsers) {
      if (user.getId().equals(tempAppToUser.getUserId())) {
        return;
      }
    }
    String path=session.getServletContext().getContextPath();
    try {
      response.sendRedirect(path + "/resources/error/noPower.jsp?appId=" + appId);
    }
 catch (    IOException e) {
      logger.error(e.getMessage(),e);
    }
  }
}
