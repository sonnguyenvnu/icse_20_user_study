@Override public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {
  String methodName=AsyncInitBeanHolder.getAsyncInitMethodName(moduleName,beanName);
  if (methodName == null || methodName.length() == 0) {
    return bean;
  }
  ProxyFactory proxyFactory=new ProxyFactory();
  proxyFactory.setTargetClass(bean.getClass());
  proxyFactory.setProxyTargetClass(true);
  AsyncInitializeBeanMethodInvoker asyncInitializeBeanMethodInvoker=new AsyncInitializeBeanMethodInvoker(bean,beanName,methodName);
  proxyFactory.addAdvice(asyncInitializeBeanMethodInvoker);
  return proxyFactory.getProxy();
}
