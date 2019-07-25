private void init(int uid,String appName,String appPackageName){
  if (mAppSwitchView == null) {
    mAppSwitchView=new AppSwitchView(this);
    mAppSwitchView.show();
  }
  mAppSwitchView.updateAppInfo(appName,appPackageName);
  if (mAppMonitor == null) {
    mAppMonitor=new AppMonitor(this,new AppMonitor.OnAppListener(){
      @Override public void onAppChanged(      String appName,      String packageName){
        if (mAppSwitchView != null) {
          mAppSwitchView.updateAppInfo(appName,packageName);
        }
      }
    }
);
    mAppMonitor.updateUid(uid).start();
  }
 else {
    mAppMonitor.updateUid(uid);
  }
}
