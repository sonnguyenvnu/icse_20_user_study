@Override public void register(final PropertyEventSubscriber<T> listener){
  _thread.send(new PropertyEvent("PropertyEventBus.registerAll"){
    @Override public void innerRun(){
      _allPropertySubscribers.add(listener);
    }
  }
);
}
