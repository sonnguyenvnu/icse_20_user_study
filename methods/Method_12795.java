/** 
 * used in PluginPackageManager, do not invoke it from outside.
 */
@Deprecated public List<ResolveInfo> queryBroadcastReceivers(Intent intent,int flags){
  List<ResolveInfo> resolveInfos=new ArrayList<ResolveInfo>();
  for (  LoadedPlugin plugin : this.mPlugins.values()) {
    List<ResolveInfo> result=plugin.queryBroadcastReceivers(intent,flags);
    if (null != result && result.size() > 0) {
      resolveInfos.addAll(result);
    }
  }
  return resolveInfos;
}
