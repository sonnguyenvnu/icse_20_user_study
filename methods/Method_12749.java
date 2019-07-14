public List<ResolveInfo> queryBroadcastReceivers(Intent intent,int flags){
  ComponentName component=intent.getComponent();
  List<ResolveInfo> resolveInfos=new ArrayList<ResolveInfo>();
  ContentResolver resolver=this.mPluginContext.getContentResolver();
  for (  PackageParser.Activity receiver : this.mPackage.receivers) {
    if (receiver.getComponentName().equals(component)) {
      ResolveInfo resolveInfo=new ResolveInfo();
      resolveInfo.activityInfo=receiver.info;
      resolveInfos.add(resolveInfo);
    }
 else     if (component == null) {
      for (      PackageParser.ActivityIntentInfo intentInfo : receiver.intents) {
        if (intentInfo.match(resolver,intent,true,TAG) >= 0) {
          ResolveInfo resolveInfo=new ResolveInfo();
          resolveInfo.activityInfo=receiver.info;
          resolveInfos.add(resolveInfo);
          break;
        }
      }
    }
  }
  return resolveInfos;
}
