@Override public void onActivityDestroyed(Activity activity){
  activeLifecycleHandlers.remove(activity);
}
