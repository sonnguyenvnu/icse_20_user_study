public void unregister(Object listener){
  Map<Class<?>,Map<Byte,Set<Method>>> handler=findHandlers(listener);
  lock.lock();
  try {
    for (    Map.Entry<Class<?>,Map<Byte,Set<Method>>> e : handler.entrySet()) {
      Map<Byte,Map<Object,Method[]>> prioritiesMap=byListenerAndPriority.get(e.getKey());
      if (prioritiesMap != null) {
        for (        Byte priority : e.getValue().keySet()) {
          Map<Object,Method[]> currentPriority=prioritiesMap.get(priority);
          if (currentPriority != null) {
            currentPriority.remove(listener);
            if (currentPriority.isEmpty()) {
              prioritiesMap.remove(priority);
            }
          }
        }
        if (prioritiesMap.isEmpty()) {
          byListenerAndPriority.remove(e.getKey());
        }
      }
      bakeHandlers(e.getKey());
    }
  }
  finally {
    lock.unlock();
  }
}
