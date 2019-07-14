public ServiceProxy findServiceProxy(ClassLoader clientClassloader,Contract contract){
  String interfaceType=contract.getInterfaceType().getCanonicalName();
  String uniqueId=contract.getUniqueId();
  for (  SofaRuntimeManager sofaRuntimeManager : SofaFramework.getRuntimeSet()) {
    if (sofaRuntimeManager.getAppClassLoader().equals(clientClassloader)) {
      continue;
    }
    Biz biz=getBiz(sofaRuntimeManager);
    if (biz != null && biz.getBizState() == BizState.ACTIVATED) {
      ServiceComponent serviceComponent=findServiceComponent(uniqueId,interfaceType,sofaRuntimeManager.getComponentManager());
      if (serviceComponent != null) {
        JvmBinding referenceJvmBinding=(JvmBinding)contract.getBinding(JvmBinding.JVM_BINDING_TYPE);
        JvmBinding serviceJvmBinding=(JvmBinding)serviceComponent.getService().getBinding(JvmBinding.JVM_BINDING_TYPE);
        boolean serialize=referenceJvmBinding.getJvmBindingParam().isSerialize() || serviceJvmBinding.getJvmBindingParam().isSerialize();
        return new DynamicJvmServiceInvoker(clientClassloader,sofaRuntimeManager.getAppClassLoader(),serviceComponent.getService().getTarget(),contract,biz.getIdentity(),serialize);
      }
    }
  }
  return null;
}
