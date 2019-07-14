@Nullable private static LifecycleHandler findInActivity(@NonNull Activity activity){
  LifecycleHandler lifecycleHandler=activeLifecycleHandlers.get(activity);
  if (lifecycleHandler == null) {
    lifecycleHandler=(LifecycleHandler)activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
  }
  if (lifecycleHandler != null) {
    lifecycleHandler.registerActivityListener(activity);
  }
  return lifecycleHandler;
}
