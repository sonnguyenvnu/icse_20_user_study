public Intent getLeanbackLaunchIntent(){
  ContentResolver resolver=this.mPluginContext.getContentResolver();
  Intent launcher=new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
  for (  PackageParser.Activity activity : this.mPackage.activities) {
    for (    PackageParser.ActivityIntentInfo intentInfo : activity.intents) {
      if (intentInfo.match(resolver,launcher,false,TAG) > 0) {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.setComponent(activity.getComponentName());
        intent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);
        return intent;
      }
    }
  }
  return null;
}
