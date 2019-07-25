/** 
 * mark the session as finished for the registry. The resources that were associated to it are now free to be reserved by other tests
 * @param session The session
 * @param reason  the reason for the release
 */
private void release(TestSession session,SessionTerminationReason reason){
  try {
    lock.lock();
    boolean removed=activeTestSessions.remove(session,reason);
    if (removed) {
      fireMatcherStateChanged();
    }
  }
  finally {
    lock.unlock();
  }
}
