@Override public @Nullable Map<String,String> queryKeyStatus(){
  return sessionId == null ? null : mediaDrm.queryKeyStatus(sessionId);
}
