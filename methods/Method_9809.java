/** 
 * Removes the specified session.
 * @param session the specified session
 */
private void removeSession(final Session session){
  final JSONObject user=(JSONObject)Channels.getHttpSessionAttribute(session,User.USER);
  if (null == user) {
    return;
  }
  final String userId=user.optString(Keys.OBJECT_ID);
  final BeanManager beanManager=BeanManager.getInstance();
  final UserMgmtService userMgmtService=beanManager.getReference(UserMgmtService.class);
  final String ip=(String)Channels.getHttpSessionAttribute(session,Common.IP);
  Set<Session> userSessions=SESSIONS.get(userId);
  if (null == userSessions) {
    userMgmtService.updateOnlineStatus(userId,ip,false,false);
    return;
  }
  userSessions.remove(session);
  if (userSessions.isEmpty()) {
    userMgmtService.updateOnlineStatus(userId,ip,false,false);
    return;
  }
}
