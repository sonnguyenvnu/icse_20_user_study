protected void injectActivity(Activity activity){
  final Intent intent=activity.getIntent();
  if (PluginUtil.isIntentFromPlugin(intent)) {
    Context base=activity.getBaseContext();
    try {
      LoadedPlugin plugin=this.mPluginManager.getLoadedPlugin(intent);
      Reflector.with(base).field("mResources").set(plugin.getResources());
      Reflector reflector=Reflector.with(activity);
      reflector.field("mBase").set(plugin.createPluginContext(activity.getBaseContext()));
      reflector.field("mApplication").set(plugin.getApplication());
      ActivityInfo activityInfo=plugin.getActivityInfo(PluginUtil.getComponent(intent));
      if (activityInfo.screenOrientation != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
        activity.setRequestedOrientation(activityInfo.screenOrientation);
      }
      ComponentName component=PluginUtil.getComponent(intent);
      Intent wrapperIntent=new Intent(intent);
      wrapperIntent.setClassName(component.getPackageName(),component.getClassName());
      activity.setIntent(wrapperIntent);
    }
 catch (    Exception e) {
      Log.w(TAG,e);
    }
  }
}
