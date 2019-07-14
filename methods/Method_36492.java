private void doUninstallBiz(BizEvent event){
  SofaRuntimeProperties.unRegisterProperties(event.getBiz().getBizClassLoader());
  SofaRuntimeManager sofaRuntimeManager=getSofaRuntimeManager(event.getBiz());
  SofaFramework.unRegisterSofaRuntimeManager(sofaRuntimeManager);
  sofaRuntimeManager.shutdown();
}
