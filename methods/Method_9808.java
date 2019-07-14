/** 
 * Called when a message received from the browser.
 * @param message message
 * @param session session
 */
@OnMessage public void onMessage(final String message,final Session session){
  JSONObject user=(JSONObject)Channels.getHttpSessionAttribute(session,User.USER);
  if (null == user) {
    return;
  }
  final String userId=user.optString(Keys.OBJECT_ID);
  final BeanManager beanManager=BeanManager.getInstance();
  final UserMgmtService userMgmtService=beanManager.getReference(UserMgmtService.class);
  final String ip=(String)Channels.getHttpSessionAttribute(session,Common.IP);
  userMgmtService.updateOnlineStatus(userId,ip,true,true);
}
