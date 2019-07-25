public void release(){
  Context applicationContext=mContext.getApplicationContext();
  if (applicationContext != null) {
    if (mServiceConnection != null) {
      try {
        applicationContext.unbindService(mServiceConnection);
      }
 catch (      Exception e) {
      }
      mServiceConnection=null;
    }
    Intent intent=new Intent(applicationContext,PluginPackageManagerService.class);
    applicationContext.stopService(intent);
  }
}
