/** 
 * Unregisters the given handler for the given class.
 * @param clazz the class
 * @param handler the handler
 */
public void unsubscribe(Class<?> clazz,EventHandler handler){
  checkNotNull(clazz);
  checkNotNull(handler);
  lock.writeLock().lock();
  try {
    handlersByType.remove(clazz,handler);
  }
  finally {
    lock.writeLock().unlock();
  }
}
