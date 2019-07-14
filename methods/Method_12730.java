private void dispatchStubActivity(Intent intent){
  ComponentName component=intent.getComponent();
  String targetClassName=intent.getComponent().getClassName();
  LoadedPlugin loadedPlugin=mPluginManager.getLoadedPlugin(intent);
  ActivityInfo info=loadedPlugin.getActivityInfo(component);
  if (info == null) {
    throw new RuntimeException("can not find " + component);
  }
  int launchMode=info.launchMode;
  Resources.Theme themeObj=loadedPlugin.getResources().newTheme();
  themeObj.applyStyle(info.theme,true);
  String stubActivity=mStubActivityInfo.getStubActivity(targetClassName,launchMode,themeObj);
  Log.i(TAG,String.format("dispatchStubActivity,[%s -> %s]",targetClassName,stubActivity));
  intent.setClassName(mContext,stubActivity);
}
