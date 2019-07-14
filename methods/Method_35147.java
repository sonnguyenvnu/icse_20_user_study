private void performOnRestoreInstanceState(){
  if (savedInstanceState != null && router != null) {
    onRestoreInstanceState(savedInstanceState);
    List<LifecycleListener> listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.onRestoreInstanceState(this,savedInstanceState);
    }
    savedInstanceState=null;
  }
}
