/** 
 * for check fields and parameters of consumer config
 */
protected void checkParameters(){
  Class proxyClass=providerConfig.getProxyClass();
  String key=providerConfig.buildKey();
  T ref=providerConfig.getRef();
  if (!proxyClass.isInstance(ref)) {
    throw ExceptionUtils.buildRuntime("provider.ref",ref == null ? "null" : ref.getClass().getName(),"This is not an instance of " + providerConfig.getInterfaceId() + " in provider config with key " + key + " !");
  }
  if (CommonUtils.isEmpty(providerConfig.getServer())) {
    throw ExceptionUtils.buildRuntime("server","NULL","Value of \"server\" is not specified in provider" + " config with key " + key + " !");
  }
  checkMethods(proxyClass);
}
