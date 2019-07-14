protected void overridePendingTransition(Method method,Object[] args){
  String hostPackageName=mPluginManager.getHostContext().getPackageName();
  args[1]=hostPackageName;
}
