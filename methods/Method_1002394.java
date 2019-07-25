@Override public void register(final Set<String> propertyNames,final PropertyEventSubscriber<T> subscriber){
  _thread.send(new PropertyEvent("PropertyEventBus.register " + propertyNames){
    public void innerRun(){
      for (      final String prop : propertyNames) {
        boolean initialized;
        boolean notifyPublisher=false;
        initialized=_properties.containsKey(prop);
        List<PropertyEventSubscriber<T>> listeners=_subscribers.get(prop);
        if (listeners == null) {
          listeners=new ArrayList<PropertyEventSubscriber<T>>();
          _subscribers.put(prop,listeners);
        }
        if (listeners.isEmpty()) {
          notifyPublisher=true;
        }
        listeners.add(subscriber);
        if (initialized) {
          subscriber.onInitialize(prop,_properties.get(prop));
        }
        if (notifyPublisher) {
          _publisher.startPublishing(prop);
        }
      }
    }
  }
);
}
