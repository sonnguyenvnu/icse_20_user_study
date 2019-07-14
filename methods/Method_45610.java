/** 
 * ???????
 * @param eventClass ????
 * @param subscriber ???
 */
public static void register(Class<? extends Event> eventClass,Subscriber subscriber){
  CopyOnWriteArraySet<Subscriber> set=SUBSCRIBER_MAP.get(eventClass);
  if (set == null) {
    set=new CopyOnWriteArraySet<Subscriber>();
    CopyOnWriteArraySet<Subscriber> old=SUBSCRIBER_MAP.putIfAbsent(eventClass,set);
    if (old != null) {
      set=old;
    }
  }
  set.add(subscriber);
  if (LOGGER.isDebugEnabled()) {
    LOGGER.debug("Register subscriber: {} of event: {}.",subscriber,eventClass);
  }
}
