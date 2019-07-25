/** 
 * Creates a proxied Spring bean. Called from within WebApp code by modification of Spring classes
 * @param beanFactry Spring beanFactory
 * @param bean Spring bean
 * @param paramClasses Parameter Classes of the Spring beanFactory method which returned the bean. The method is named ProxyReplacer.FACTORY_METHOD_NAME
 * @param paramValues Parameter values of the Spring beanFactory method which returned the bean. The method is named ProxyReplacer.FACTORY_METHOD_NAME
 * @return Proxied bean
 */
public static Object register(Object beanFactry,Object bean,Class<?>[] paramClasses,Object[] paramValues){
  if (bean == null) {
    return bean;
  }
  if (SpringPlugin.basePackagePrefixes != null) {
    boolean hasMatch=false;
    for (    String basePackagePrefix : SpringPlugin.basePackagePrefixes) {
      if (bean.getClass().getName().startsWith(basePackagePrefix)) {
        hasMatch=true;
        break;
      }
    }
    if (!hasMatch) {
      LOGGER.info("{} not in basePackagePrefix",bean.getClass().getName());
      return bean;
    }
  }
  if (bean.getClass().getName().startsWith("com.sun.proxy.$Proxy")) {
    InvocationHandler handler=new HotswapSpringInvocationHandler(bean,beanFactry,paramClasses,paramValues);
    Class<?>[] interfaces=bean.getClass().getInterfaces();
    try {
      if (!Arrays.asList(interfaces).contains(getInfrastructureProxyClass())) {
        interfaces=Arrays.copyOf(interfaces,interfaces.length + 1);
        interfaces[interfaces.length - 1]=getInfrastructureProxyClass();
      }
    }
 catch (    ClassNotFoundException e) {
      LOGGER.error("error adding org.springframework.core.InfrastructureProxy to proxy class",e);
    }
    return Proxy.newProxyInstance(beanFactry.getClass().getClassLoader(),interfaces,handler);
  }
 else   if (EnhancerProxyCreater.isSupportedCglibProxy(bean)) {
    if (bean.getClass().getName().contains("$HOTSWAPAGENT_")) {
      return bean;
    }
    return EnhancerProxyCreater.createProxy(beanFactry,bean,paramClasses,paramValues);
  }
  return bean;
}
