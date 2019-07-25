@Override public void save(HazelcastSession session){
  if (session.isNew) {
    this.sessions.set(session.getId(),session.getDelegate(),session.getMaxInactiveInterval().getSeconds(),TimeUnit.SECONDS);
  }
 else   if (session.sessionIdChanged) {
    this.sessions.delete(session.originalId);
    session.originalId=session.getId();
    this.sessions.set(session.getId(),session.getDelegate(),session.getMaxInactiveInterval().getSeconds(),TimeUnit.SECONDS);
  }
 else   if (session.hasChanges()) {
    SessionUpdateEntryProcessor entryProcessor=new SessionUpdateEntryProcessor();
    if (session.lastAccessedTimeChanged) {
      entryProcessor.setLastAccessedTime(session.getLastAccessedTime());
    }
    if (session.maxInactiveIntervalChanged) {
      if (SUPPORTS_SET_TTL) {
        updateTtl(session);
      }
      entryProcessor.setMaxInactiveInterval(session.getMaxInactiveInterval());
    }
    if (!session.delta.isEmpty()) {
      entryProcessor.setDelta(session.delta);
    }
    this.sessions.executeOnKey(session.getId(),entryProcessor);
  }
  session.clearChangeFlags();
}
