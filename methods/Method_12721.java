private ContentProvider getContentProvider(final Uri uri){
  final PluginManager pluginManager=PluginManager.getInstance(getContext());
  Uri pluginUri=Uri.parse(uri.getQueryParameter(KEY_URI));
  final String auth=pluginUri.getAuthority();
  ContentProvider cachedProvider=sCachedProviders.get(auth);
  if (cachedProvider != null) {
    return cachedProvider;
  }
synchronized (sCachedProviders) {
    LoadedPlugin plugin=pluginManager.getLoadedPlugin(uri.getQueryParameter(KEY_PKG));
    if (plugin == null) {
      try {
        pluginManager.loadPlugin(new File(uri.getQueryParameter(KEY_PLUGIN)));
      }
 catch (      Exception e) {
        Log.w(TAG,e);
      }
    }
    final ProviderInfo providerInfo=pluginManager.resolveContentProvider(auth,0);
    if (providerInfo != null) {
      RunUtil.runOnUiThread(new Runnable(){
        @Override public void run(){
          try {
            LoadedPlugin loadedPlugin=pluginManager.getLoadedPlugin(uri.getQueryParameter(KEY_PKG));
            ContentProvider contentProvider=(ContentProvider)Class.forName(providerInfo.name).newInstance();
            contentProvider.attachInfo(loadedPlugin.getPluginContext(),providerInfo);
            sCachedProviders.put(auth,contentProvider);
          }
 catch (          Exception e) {
            Log.w(TAG,e);
          }
        }
      }
,true);
      return sCachedProviders.get(auth);
    }
  }
  return null;
}
