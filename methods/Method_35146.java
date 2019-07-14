private void performDestroy(){
  if (isContextAvailable) {
    List<LifecycleListener> listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.preContextUnavailable(this,getActivity());
    }
    isContextAvailable=false;
    onContextUnavailable();
    listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.postContextUnavailable(this);
    }
  }
  if (!destroyed) {
    List<LifecycleListener> listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.preDestroy(this);
    }
    destroyed=true;
    onDestroy();
    parentController=null;
    listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.postDestroy(this);
    }
  }
}
