/** 
 * ??????
 * @param sessionId
 * @param onlineStatus
 */
public void updateStatus(Serializable sessionId,UpmsSession.OnlineStatus onlineStatus){
  UpmsSession session=(UpmsSession)doReadSession(sessionId);
  if (null == session) {
    return;
  }
  session.setStatus(onlineStatus);
  RedisUtil.set(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + session.getId(),SerializableUtil.serialize(session),(int)session.getTimeout() / 1000);
}
