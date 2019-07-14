@Override public void provisionRequired(DefaultDrmSession<T> session){
  if (provisioningSessions.contains(session)) {
    return;
  }
  provisioningSessions.add(session);
  if (provisioningSessions.size() == 1) {
    session.provision();
  }
}
