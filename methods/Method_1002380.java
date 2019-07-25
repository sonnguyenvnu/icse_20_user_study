public void unregister(final SimpleLoadBalancerStateListener listener){
  trace(_log,"unregister listener: ",listener);
  _executor.execute(new PropertyEvent("remove listener for state"){
    @Override public void innerRun(){
      _listeners.remove(listener);
    }
  }
);
}
