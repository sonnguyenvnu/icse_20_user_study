protected Object stopService(Object proxy,Method method,Object[] args) throws Throwable {
  Intent target=(Intent)args[1];
  ResolveInfo resolveInfo=this.mPluginManager.resolveService(target,0);
  if (null == resolveInfo || null == resolveInfo.serviceInfo) {
    return method.invoke(this.mActivityManager,args);
  }
  startDelegateServiceForTarget(target,resolveInfo.serviceInfo,null,RemoteService.EXTRA_COMMAND_STOP_SERVICE);
  return 1;
}
