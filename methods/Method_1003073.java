@Override public SessionInterface reconnect(boolean write){
  readSessionState();
  close();
  Session newSession=Engine.getInstance().createSession(connectionInfo);
  newSession.sessionState=sessionState;
  newSession.recreateSessionState();
  if (write) {
    while (!newSession.database.beforeWriting()) {
    }
  }
  return newSession;
}
