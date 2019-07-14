/** 
 * Processes the event and logs if an exception is thrown. 
 */
@SuppressWarnings("PMD.SwitchStmtsShouldHaveDefault") public void dispatch(@NonNull JCacheEntryEvent<K,V> event){
  try {
    if (event.getSource().isClosed()) {
      return;
    }
switch (event.getEventType()) {
case CREATED:
      onCreated(event);
    return;
case UPDATED:
  onUpdated(event);
return;
case REMOVED:
onRemoved(event);
return;
case EXPIRED:
onExpired(event);
return;
}
throw new IllegalStateException("Unknown event type: " + event.getEventType());
}
 catch (Exception e) {
logger.log(Level.WARNING,null,e);
}
catch (Throwable t) {
logger.log(Level.SEVERE,null,t);
}
}
