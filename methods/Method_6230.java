public static void bindCustomTabsService(Activity activity){
  Activity currentActivity=currentCustomTabsActivity == null ? null : currentCustomTabsActivity.get();
  if (currentActivity != null && currentActivity != activity) {
    unbindCustomTabsService(currentActivity);
  }
  if (customTabsClient != null) {
    return;
  }
  currentCustomTabsActivity=new WeakReference<>(activity);
  try {
    if (TextUtils.isEmpty(customTabsPackageToBind)) {
      customTabsPackageToBind=CustomTabsHelper.getPackageNameToUse(activity);
      if (customTabsPackageToBind == null) {
        return;
      }
    }
    customTabsServiceConnection=new ServiceConnection(new ServiceConnectionCallback(){
      @Override public void onServiceConnected(      CustomTabsClient client){
        customTabsClient=client;
        if (SharedConfig.customTabs) {
          if (customTabsClient != null) {
            try {
              customTabsClient.warmup(0);
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
        }
      }
      @Override public void onServiceDisconnected(){
        customTabsClient=null;
      }
    }
);
    if (!CustomTabsClient.bindCustomTabsService(activity,customTabsPackageToBind,customTabsServiceConnection)) {
      customTabsServiceConnection=null;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
