/** 
 * Refer t.
 * @return the t
 * @throws SofaRpcRuntimeException the init error exception
 */
@Override public synchronized T refer(){
  if (proxyIns != null) {
    return proxyIns;
  }
  referenceConfig=new ReferenceConfig<T>();
  covert(consumerConfig,referenceConfig);
  proxyIns=referenceConfig.get();
  return proxyIns;
}
