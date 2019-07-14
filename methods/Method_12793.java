public ResolveInfo resolveService(Intent intent,int flags){
  for (  LoadedPlugin plugin : this.mPlugins.values()) {
    ResolveInfo resolveInfo=plugin.resolveService(intent,flags);
    if (null != resolveInfo) {
      return resolveInfo;
    }
  }
  return null;
}
