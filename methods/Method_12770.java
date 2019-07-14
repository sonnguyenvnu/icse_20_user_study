public static int getTheme(Context context,ComponentName component){
  LoadedPlugin loadedPlugin=PluginManager.getInstance(context).getLoadedPlugin(component);
  if (null == loadedPlugin) {
    return 0;
  }
  ActivityInfo info=loadedPlugin.getActivityInfo(component);
  if (null == info) {
    return 0;
  }
  if (0 != info.theme) {
    return info.theme;
  }
  ApplicationInfo appInfo=info.applicationInfo;
  if (null != appInfo && appInfo.theme != 0) {
    return appInfo.theme;
  }
  return selectDefaultTheme(0,Build.VERSION.SDK_INT);
}
