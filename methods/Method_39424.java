/** 
 * Returns scoped proxy bean if injection scopes are mixed on some injection point. May return <code>null</code> if mixing scopes is not detected.
 */
public Object lookupValue(final PetiteContainer petiteContainer,final BeanDefinition targetBeanDefinition,final BeanDefinition refBeanDefinition){
  Scope targetScope=targetBeanDefinition.scope;
  Scope refBeanScope=refBeanDefinition.scope;
  boolean detectMixedScopes=petiteContainer.config().isDetectMixedScopes();
  boolean wireScopedProxy=petiteContainer.config().isWireScopedProxy();
  if (targetScope != null && !targetScope.accept(refBeanScope)) {
    if (!wireScopedProxy) {
      if (detectMixedScopes) {
        throw new PetiteException(createMixingMessage(targetBeanDefinition,refBeanDefinition));
      }
      return null;
    }
    if (detectMixedScopes) {
      if (log.isWarnEnabled()) {
        log.warn(createMixingMessage(targetBeanDefinition,refBeanDefinition));
      }
    }
 else {
      if (log.isDebugEnabled()) {
        log.debug(createMixingMessage(targetBeanDefinition,refBeanDefinition));
      }
    }
    String scopedProxyBeanName=refBeanDefinition.name;
    Object proxy=proxies.get(scopedProxyBeanName);
    if (proxy == null) {
      proxy=createScopedProxyBean(petiteContainer,refBeanDefinition);
      proxies.put(scopedProxyBeanName,proxy);
    }
    return proxy;
  }
  return null;
}
