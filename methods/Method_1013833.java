/** 
 * Observe for session change, see frontend() & backend() receives SessionStateChangeEvent | TunnelStateChangeEvent
 */
@Override public void update(Object args,Observable observable){
  if (!(observable instanceof Session)) {
    logger.error("[update] Tunnel should only observe session, not {}",observable.getClass().getName());
    return;
  }
  Session session=(Session)observable;
  SessionStateChangeEvent event=(SessionStateChangeEvent)args;
  if (event.getCurrent() instanceof SessionClosed) {
    onSessionClosed(session);
  }
 else   if (event.getCurrent() instanceof SessionEstablished) {
    onSessionEstablished(session);
  }
 else {
    logger.info("[update] un-recognised event: {}",event);
  }
}
