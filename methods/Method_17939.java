@GuardedBy("sMountContentLock") private static void ensureActivityCallbacks(Context context){
  if (sActivityCallbacks == null && !sIsManualCallbacks) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      throw new RuntimeException("Activity callbacks must be invoked manually below ICS (API level 14)");
    }
    sActivityCallbacks=new PoolsActivityCallback();
    ((Application)context.getApplicationContext()).registerActivityLifecycleCallbacks(sActivityCallbacks);
  }
}
