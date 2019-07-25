public void register(final SimpleLoadBalancerStateListener listener){
  trace(_log,"register listener: ",listener);
  _executor.execute(new PropertyEvent("add listener for state"){
    @Override public void innerRun(){
      _listeners.add(listener);
    }
  }
);
}
