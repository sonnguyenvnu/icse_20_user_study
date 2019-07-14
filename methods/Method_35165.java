private void registerActivityListener(@NonNull Activity activity){
  this.activity=activity;
  if (!hasRegisteredCallbacks) {
    hasRegisteredCallbacks=true;
    activity.getApplication().registerActivityLifecycleCallbacks(this);
    activeLifecycleHandlers.put(activity,this);
  }
}
