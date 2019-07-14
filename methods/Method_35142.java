final void onContextAvailable(){
  final Context context=router.getActivity();
  if (context != null && !isContextAvailable) {
    List<LifecycleListener> listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.preContextAvailable(this);
    }
    isContextAvailable=true;
    onContextAvailable(context);
    listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.postContextAvailable(this,context);
    }
  }
  for (  Router childRouter : childRouters) {
    childRouter.onContextAvailable();
  }
}
