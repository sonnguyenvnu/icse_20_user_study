/** 
 * Called when the socket connection with the browser is established.
 * @param session session
 */
@OnOpen public void onConnect(final Session session){
  final JSONObject user=(JSONObject)Channels.getHttpSessionAttribute(session,User.USER);
  if (null == user) {
    return;
  }
  final String userId=user.optString(Keys.OBJECT_ID);
  Set<Session> userSessions=SESSIONS.get(userId);
  if (null == userSessions) {
    userSessions=Collections.newSetFromMap(new ConcurrentHashMap());
  }
  userSessions.add(session);
  SESSIONS.put(userId,userSessions);
  final BeanManager beanManager=BeanManager.getInstance();
  final UserMgmtService userMgmtService=beanManager.getReference(UserMgmtService.class);
  final String ip=(String)Channels.getHttpSessionAttribute(session,Common.IP);
  userMgmtService.updateOnlineStatus(userId,ip,true,true);
}
