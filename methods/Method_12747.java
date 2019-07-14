public ResolveInfo resolveService(Intent intent,int flags){
  List<ResolveInfo> query=this.queryIntentServices(intent,flags);
  if (null == query || query.isEmpty()) {
    return null;
  }
  ContentResolver resolver=this.mPluginContext.getContentResolver();
  return chooseBestActivity(intent,intent.resolveTypeIfNeeded(resolver),flags,query);
}
