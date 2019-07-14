@Override public void onActivitySaveInstanceState(Activity activity,Bundle outState){
  Object[] callbacks=collectActivityLifecycleCallbacks();
  if (callbacks != null) {
    for (int i=0; i < callbacks.length; i++) {
      ((Application.ActivityLifecycleCallbacks)callbacks[i]).onActivitySaveInstanceState(activity,outState);
    }
  }
}
