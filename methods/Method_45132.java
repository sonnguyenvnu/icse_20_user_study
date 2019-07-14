private Iterable<Session> toUniqueSessions(final Session targetSession,final ImmutableList<Session> sessions){
  Optional<Session> session=tryFind(sessions,isForRequest(targetSession.getRequest()));
  if (session.isPresent()) {
    return from(sessions).filter(not(isForRequest(targetSession.getRequest())));
  }
  return sessions;
}
