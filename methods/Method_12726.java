@Override public void onActivityStopped(Activity activity){
  Object[] callbacks=collectActivityLifecycleCallbacks();
  if (callbacks != null) {
    for (int i=0; i < callbacks.length; i++) {
      ((Application.ActivityLifecycleCallbacks)callbacks[i]).onActivityStopped(activity);
    }
  }
}
