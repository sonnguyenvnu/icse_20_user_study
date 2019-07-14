/** 
 * Creates proxy object.
 */
protected C createProxyObject(Class<C> target){
  target=ProxettaUtil.resolveTargetClass(target);
  Class proxyClass=cache.get(target);
  if (proxyClass == null) {
    proxyClass=proxetta.defineProxy(target);
    cache.put(target,proxyClass);
  }
  final C proxy;
  try {
    proxy=(C)ClassUtil.newInstance(proxyClass);
  }
 catch (  final Exception ex) {
    throw new PathrefException(ex);
  }
  return proxy;
}
