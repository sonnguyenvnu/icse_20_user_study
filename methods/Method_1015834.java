public void register(Object listener){
  Map<Class<?>,Map<Byte,Set<Method>>> handler=findHandlers(listener);
  lock.lock();
  try {
    for (    Map.Entry<Class<?>,Map<Byte,Set<Method>>> e : handler.entrySet()) {
      Map<Byte,Map<Object,Method[]>> prioritiesMap=byListenerAndPriority.get(e.getKey());
      if (prioritiesMap == null) {
        prioritiesMap=new HashMap<>();
        byListenerAndPriority.put(e.getKey(),prioritiesMap);
      }
      for (      Map.Entry<Byte,Set<Method>> entry : e.getValue().entrySet()) {
        Map<Object,Method[]> currentPriorityMap=prioritiesMap.get(entry.getKey());
        if (currentPriorityMap == null) {
          currentPriorityMap=new HashMap<>();
          prioritiesMap.put(entry.getKey(),currentPriorityMap);
        }
        Method[] baked=new Method[entry.getValue().size()];
        currentPriorityMap.put(listener,entry.getValue().toArray(baked));
      }
      bakeHandlers(e.getKey());
    }
  }
  finally {
    lock.unlock();
  }
}
