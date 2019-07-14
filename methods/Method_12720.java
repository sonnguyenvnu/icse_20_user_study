private void wrapperUri(Method method,Object[] args){
  Uri uri=null;
  int index=0;
  if (args != null) {
    for (int i=0; i < args.length; i++) {
      if (args[i] instanceof Uri) {
        uri=(Uri)args[i];
        index=i;
        break;
      }
    }
  }
  Bundle bundleInCallMethod=null;
  if (method.getName().equals("call")) {
    bundleInCallMethod=getBundleParameter(args);
    if (bundleInCallMethod != null) {
      String uriString=bundleInCallMethod.getString(RemoteContentProvider.KEY_WRAPPER_URI);
      if (uriString != null) {
        uri=Uri.parse(uriString);
      }
    }
  }
  if (uri == null) {
    return;
  }
  PluginManager pluginManager=PluginManager.getInstance(mContext);
  ProviderInfo info=pluginManager.resolveContentProvider(uri.getAuthority(),0);
  if (info != null) {
    String pkg=info.packageName;
    LoadedPlugin plugin=pluginManager.getLoadedPlugin(pkg);
    Uri wrapperUri=PluginContentResolver.wrapperUri(plugin,uri);
    if (method.getName().equals("call")) {
      bundleInCallMethod.putString(RemoteContentProvider.KEY_WRAPPER_URI,wrapperUri.toString());
    }
 else {
      args[index]=wrapperUri;
    }
  }
}
