public ResolveInfo resolveActivity(Intent intent,int flags){
  for (  LoadedPlugin plugin : this.mPlugins.values()) {
    ResolveInfo resolveInfo=plugin.resolveActivity(intent,flags);
    if (null != resolveInfo) {
      return resolveInfo;
    }
  }
  return null;
}
