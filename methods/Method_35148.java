final void changeStarted(@NonNull ControllerChangeHandler changeHandler,@NonNull ControllerChangeType changeType){
  if (!changeType.isEnter) {
    isPerformingExitTransition=true;
    for (    ControllerHostedRouter router : childRouters) {
      router.setDetachFrozen(true);
    }
  }
  onChangeStarted(changeHandler,changeType);
  List<LifecycleListener> listeners=new ArrayList<>(lifecycleListeners);
  for (  LifecycleListener lifecycleListener : listeners) {
    lifecycleListener.onChangeStart(this,changeHandler,changeType);
  }
}
