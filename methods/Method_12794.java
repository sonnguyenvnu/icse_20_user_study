public ProviderInfo resolveContentProvider(String name,int flags){
  for (  LoadedPlugin plugin : this.mPlugins.values()) {
    ProviderInfo providerInfo=plugin.resolveContentProvider(name,flags);
    if (null != providerInfo) {
      return providerInfo;
    }
  }
  return null;
}
