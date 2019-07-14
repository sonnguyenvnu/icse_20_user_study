public static void hookActivityResources(Activity activity,String packageName){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isVivo(activity.getResources())) {
    return;
  }
  try {
    Context base=activity.getBaseContext();
    final LoadedPlugin plugin=PluginManager.getInstance(activity).getLoadedPlugin(packageName);
    final Resources resources=plugin.getResources();
    if (resources != null) {
      Reflector.with(base).field("mResources").set(resources);
      Resources.Theme theme=resources.newTheme();
      theme.setTo(activity.getTheme());
      Reflector reflector=Reflector.with(activity);
      int themeResource=reflector.field("mThemeResource").get();
      theme.applyStyle(themeResource,true);
      reflector.field("mTheme").set(theme);
      reflector.field("mResources").set(resources);
    }
  }
 catch (  Exception e) {
    Log.w(Constants.TAG,e);
  }
}
