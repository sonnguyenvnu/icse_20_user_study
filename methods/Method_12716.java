protected Intent wrapperTargetIntent(Intent target,ServiceInfo serviceInfo,Bundle extras,int command){
  target.setComponent(new ComponentName(serviceInfo.packageName,serviceInfo.name));
  String pluginLocation=mPluginManager.getLoadedPlugin(target.getComponent()).getLocation();
  boolean local=PluginUtil.isLocalService(serviceInfo);
  Class<? extends Service> delegate=local ? LocalService.class : RemoteService.class;
  Intent intent=new Intent();
  intent.setClass(mPluginManager.getHostContext(),delegate);
  intent.putExtra(RemoteService.EXTRA_TARGET,target);
  intent.putExtra(RemoteService.EXTRA_COMMAND,command);
  intent.putExtra(RemoteService.EXTRA_PLUGIN_LOCATION,pluginLocation);
  if (extras != null) {
    intent.putExtras(extras);
  }
  return intent;
}
