private void init(@NonNull Context context){
  if (mIsInitialized) {
    return;
  }
  mContext=context.getApplicationContext();
  mPackageManager=PluginPackageManager.getInstance(mContext);
  mIsInitialized=true;
  onBindService(mContext);
}
