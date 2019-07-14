@Override public void onActivityCreated(Activity activity,Bundle savedInstanceState){
  Object[] callbacks=collectActivityLifecycleCallbacks();
  if (callbacks != null) {
    for (int i=0; i < callbacks.length; i++) {
      ((Application.ActivityLifecycleCallbacks)callbacks[i]).onActivityCreated(activity,savedInstanceState);
    }
  }
}
