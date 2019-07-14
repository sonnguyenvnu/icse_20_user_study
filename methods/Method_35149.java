final void changeEnded(@NonNull ControllerChangeHandler changeHandler,@NonNull ControllerChangeType changeType){
  if (!changeType.isEnter) {
    isPerformingExitTransition=false;
    for (    ControllerHostedRouter router : childRouters) {
      router.setDetachFrozen(false);
    }
  }
  onChangeEnded(changeHandler,changeType);
  List<LifecycleListener> listeners=new ArrayList<>(lifecycleListeners);
  for (  LifecycleListener lifecycleListener : listeners) {
    lifecycleListener.onChangeEnd(this,changeHandler,changeType);
  }
  if (isBeingDestroyed && !viewIsAttached && !attached && destroyedView != null) {
    View view=destroyedView.get();
    if (router.container != null && view != null && view.getParent() == router.container) {
      router.container.removeView(view);
    }
    destroyedView=null;
  }
  changeHandler.onEnd();
}
