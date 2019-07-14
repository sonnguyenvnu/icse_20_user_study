@Override public boolean forceLogout(String sessionId){
  Session session=sessionDAO.readSession(sessionId);
  session.setTimeout(0);
  return true;
}
