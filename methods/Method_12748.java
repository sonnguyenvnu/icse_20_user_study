public List<ResolveInfo> queryIntentServices(Intent intent,int flags){
  ComponentName component=intent.getComponent();
  List<ResolveInfo> resolveInfos=new ArrayList<ResolveInfo>();
  ContentResolver resolver=this.mPluginContext.getContentResolver();
  for (  PackageParser.Service service : this.mPackage.services) {
    if (match(service,component)) {
      ResolveInfo resolveInfo=new ResolveInfo();
      resolveInfo.serviceInfo=service.info;
      resolveInfos.add(resolveInfo);
    }
 else     if (component == null) {
      for (      PackageParser.ServiceIntentInfo intentInfo : service.intents) {
        if (intentInfo.match(resolver,intent,true,TAG) >= 0) {
          ResolveInfo resolveInfo=new ResolveInfo();
          resolveInfo.serviceInfo=service.info;
          resolveInfos.add(resolveInfo);
          break;
        }
      }
    }
  }
  return resolveInfos;
}
