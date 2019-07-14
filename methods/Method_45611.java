/** 
 * ????????
 * @param eventClass ????
 * @param subscriber ???
 */
public static void unRegister(Class<? extends Event> eventClass,Subscriber subscriber){
  CopyOnWriteArraySet<Subscriber> set=SUBSCRIBER_MAP.get(eventClass);
  if (set != null) {
    set.remove(subscriber);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("UnRegister subscriber: {} of event: {}.",subscriber,eventClass);
    }
  }
}
