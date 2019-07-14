/** 
 * Notifies listeners that an entry has been automatically removed due to expiration, eviction, or eligibility for garbage collection. This should be called every time expireEntries or evictEntry is called (once the lock is released).
 */
void processPendingNotifications(){
  RemovalNotification<K,V> notification;
  while ((notification=removalNotificationQueue.poll()) != null) {
    try {
      removalListener.onRemoval(notification);
    }
 catch (    Throwable e) {
      logger.log(Level.WARNING,"Exception thrown by removal listener",e);
    }
  }
}
