/** 
 * Returns if the backing listener consumes this type of event. 
 */
@SuppressWarnings("PMD.SwitchStmtsShouldHaveDefault") public boolean isCompatible(@NonNull EventType eventType){
switch (eventType) {
case CREATED:
    return (listener instanceof CacheEntryCreatedListener<?,?>);
case UPDATED:
  return (listener instanceof CacheEntryUpdatedListener<?,?>);
case REMOVED:
return (listener instanceof CacheEntryRemovedListener<?,?>);
case EXPIRED:
return (listener instanceof CacheEntryExpiredListener<?,?>);
}
throw new IllegalStateException("Unknown event type: " + eventType);
}
