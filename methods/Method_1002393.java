@Override public void unregister(final PropertyEventSubscriber<T> listener){
  _thread.send(new PropertyEvent("PropertyEventBus.unregisterAll"){
    @Override public void innerRun(){
      _allPropertySubscribers.remove(listener);
    }
  }
);
}
