@Override protected Serializable doCreate(Session session){
  Serializable sessionId=generateSessionId(session);
  assignSessionId(session,sessionId);
  RedisUtil.set(ZHENG_UPMS_SHIRO_SESSION_ID + "_" + sessionId,SerializableUtil.serialize(session),(int)session.getTimeout() / 1000);
  LOGGER.debug("doCreate >>>>> sessionId={}",sessionId);
  return sessionId;
}
