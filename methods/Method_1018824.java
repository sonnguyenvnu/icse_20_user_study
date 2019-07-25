/** 
 * ??????
 * @param mContext     ???Context
 * @param mProcessName ?????
 */
public static void quit(Context mContext,String mProcessName){
  PluginPackageManagerNative.getInstance(mContext).release();
  for (  Map.Entry<String,PluginLoadedApk> entry : getAllPluginLoadedApk().entrySet()) {
    PluginLoadedApk plugin=entry.getValue();
    if (plugin != null) {
      plugin.quitApp(true,false);
    }
  }
  PServiceSupervisor.clearConnections();
  Intent intent=new Intent();
  String proxyServiceName=ComponentFinder.matchServiceProxyByFeature(mProcessName);
  try {
    PluginDebugLog.runtimeLog(TAG,"try to stop service " + proxyServiceName);
    intent.setClass(mContext,Class.forName(proxyServiceName));
    intent.setAction(IntentConstant.ACTION_QUIT_SERVICE);
    mContext.startService(intent);
  }
 catch (  ClassNotFoundException e) {
    e.printStackTrace();
  }
}
