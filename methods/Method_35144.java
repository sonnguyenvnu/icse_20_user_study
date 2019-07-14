final View inflate(@NonNull ViewGroup parent){
  if (view != null && view.getParent() != null && view.getParent() != parent) {
    detach(view,true,false);
    removeViewReference();
  }
  if (view == null) {
    List<LifecycleListener> listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.preCreateView(this);
    }
    view=onCreateView(LayoutInflater.from(parent.getContext()),parent);
    if (view == parent) {
      throw new IllegalStateException("Controller's onCreateView method returned the parent ViewGroup. Perhaps you forgot to pass false for LayoutInflater.inflate's attachToRoot parameter?");
    }
    listeners=new ArrayList<>(lifecycleListeners);
    for (    LifecycleListener lifecycleListener : listeners) {
      lifecycleListener.postCreateView(this,view);
    }
    restoreViewState(view);
    viewAttachHandler=new ViewAttachHandler(new ViewAttachListener(){
      @Override public void onAttached(){
        viewIsAttached=true;
        viewWasDetached=false;
        attach(view);
      }
      @Override public void onDetached(      boolean fromActivityStop){
        viewIsAttached=false;
        viewWasDetached=true;
        if (!isDetachFrozen) {
          detach(view,false,fromActivityStop);
        }
      }
      @Override public void onViewDetachAfterStop(){
        if (!isDetachFrozen) {
          detach(view,false,false);
        }
      }
    }
);
    viewAttachHandler.listenForAttach(view);
  }
 else   if (retainViewMode == RetainViewMode.RETAIN_DETACH) {
    restoreChildControllerHosts();
  }
  return view;
}
