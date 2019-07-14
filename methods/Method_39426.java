/** 
 * Creates scoped proxy bean for given bean definition.
 */
protected Object createScopedProxyBean(final PetiteContainer petiteContainer,final BeanDefinition refBeanDefinition){
  Class beanType=refBeanDefinition.type;
  Class proxyClass=proxyClasses.get(beanType);
  if (proxyClass == null) {
    if (refBeanDefinition instanceof ProxettaBeanDefinition) {
      ProxettaBeanDefinition pbd=(ProxettaBeanDefinition)refBeanDefinition;
      ProxyProxetta proxetta=Proxetta.proxyProxetta().withAspects(ArraysUtil.insert(pbd.proxyAspects,aspect,0));
      proxetta.setClassNameSuffix("$ScopedProxy");
      proxetta.setVariableClassName(true);
      ProxyProxettaFactory builder=proxetta.proxy().setTarget(pbd.originalTarget);
      proxyClass=builder.define();
      proxyClasses.put(beanType,proxyClass);
    }
 else {
      ProxyProxetta proxetta=Proxetta.proxyProxetta().withAspect(aspect);
      proxetta.setClassNameSuffix("$ScopedProxy");
      proxetta.setVariableClassName(true);
      ProxyProxettaFactory builder=proxetta.proxy().setTarget(beanType);
      proxyClass=builder.define();
      proxyClasses.put(beanType,proxyClass);
    }
  }
  Object proxy;
  try {
    proxy=ClassUtil.newInstance(proxyClass);
    Field field=proxyClass.getField("$__petiteContainer$0");
    field.set(proxy,petiteContainer);
    field=proxyClass.getField("$__name$0");
    field.set(proxy,refBeanDefinition.name);
  }
 catch (  Exception ex) {
    throw new PetiteException(ex);
  }
  return proxy;
}
