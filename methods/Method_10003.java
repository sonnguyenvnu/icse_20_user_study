/** 
 * Fills personal navigation.
 * @param dataModel the specified data model
 */
private void fillPersonalNav(final Map<String,Object> dataModel){
  Stopwatchs.start("Fills personal nav");
  try {
    final boolean isLoggedIn=Sessions.isLoggedIn();
    dataModel.put(Common.IS_LOGGED_IN,isLoggedIn);
    dataModel.put(Common.IS_ADMIN_LOGGED_IN,false);
    if (!isLoggedIn) {
      return;
    }
    dataModel.put(Common.IS_LOGGED_IN,true);
    dataModel.put(Common.LOGOUT_URL,userQueryService.getLogoutURL("/"));
    final JSONObject curUser=Sessions.getUser();
    final String userRole=curUser.optString(User.USER_ROLE);
    dataModel.put(User.USER_ROLE,userRole);
    dataModel.put(Common.IS_ADMIN_LOGGED_IN,Role.ROLE_ID_C_ADMIN.equals(userRole));
    avatarQueryService.fillUserAvatarURL(curUser);
    final String userId=curUser.optString(Keys.OBJECT_ID);
    final long followingArticleCnt=followQueryService.getFollowingCount(userId,Follow.FOLLOWING_TYPE_C_ARTICLE);
    final long followingTagCnt=followQueryService.getFollowingCount(userId,Follow.FOLLOWING_TYPE_C_TAG);
    final long followingUserCnt=followQueryService.getFollowingCount(userId,Follow.FOLLOWING_TYPE_C_USER);
    curUser.put(Common.FOLLOWING_ARTICLE_CNT,followingArticleCnt);
    curUser.put(Common.FOLLOWING_TAG_CNT,followingTagCnt);
    curUser.put(Common.FOLLOWING_USER_CNT,followingUserCnt);
    final int point=curUser.optInt(UserExt.USER_POINT);
    final int appRole=curUser.optInt(UserExt.USER_APP_ROLE);
    if (UserExt.USER_APP_ROLE_C_HACKER == appRole) {
      curUser.put(UserExt.USER_T_POINT_HEX,Integer.toHexString(point));
    }
 else {
      curUser.put(UserExt.USER_T_POINT_CC,UserExt.toCCString(point));
    }
    dataModel.put(Common.CURRENT_USER,curUser);
    final JSONObject role=roleQueryService.getRole(userRole);
    curUser.put(Role.ROLE_NAME,role.optString(Role.ROLE_NAME));
    dataModel.put(Notification.NOTIFICATION_T_UNREAD_COUNT,0);
    dataModel.put(Common.IS_DAILY_CHECKIN,activityQueryService.isCheckedinToday(userId));
    final int livenessMax=Symphonys.ACTIVITY_YESTERDAY_REWARD_MAX;
    final int currentLiveness=livenessQueryService.getCurrentLivenessPoint(userId);
    dataModel.put(Liveness.LIVENESS,(float)(Math.round((float)currentLiveness / livenessMax * 100 * 100)) / 100);
  }
  finally {
    Stopwatchs.end();
  }
}
