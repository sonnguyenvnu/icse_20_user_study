protected void getIntentSender(Method method,Object[] args){
  String hostPackageName=mPluginManager.getHostContext().getPackageName();
  args[1]=hostPackageName;
  Intent target=((Intent[])args[5])[0];
  int intentSenderType=(int)args[0];
  if (intentSenderType == INTENT_SENDER_ACTIVITY) {
    mPluginManager.getComponentsHandler().transformIntentToExplicitAsNeeded(target);
    mPluginManager.getComponentsHandler().markIntentIfNeeded(target);
  }
 else   if (intentSenderType == INTENT_SENDER_SERVICE) {
    ResolveInfo resolveInfo=this.mPluginManager.resolveService(target,0);
    if (resolveInfo != null && resolveInfo.serviceInfo != null) {
      Intent wrapperIntent=wrapperTargetIntent(target,resolveInfo.serviceInfo,null,RemoteService.EXTRA_COMMAND_START_SERVICE);
      ((Intent[])args[5])[0]=wrapperIntent;
    }
  }
 else   if (intentSenderType == INTENT_SENDER_BROADCAST) {
  }
}
