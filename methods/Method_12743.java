public Intent getLaunchIntent(){
  ContentResolver resolver=this.mPluginContext.getContentResolver();
  Intent launcher=new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);
  for (  PackageParser.Activity activity : this.mPackage.activities) {
    for (    PackageParser.ActivityIntentInfo intentInfo : activity.intents) {
      if (intentInfo.match(resolver,launcher,false,TAG) > 0) {
        return Intent.makeMainActivity(activity.getComponentName());
      }
    }
  }
  return null;
}
