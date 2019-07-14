@Override public <T>T getProxy(Class<T> interfaceClass,Invoker proxyInvoker){
  InvocationHandler handler=new JDKInvocationHandler(interfaceClass,proxyInvoker);
  ClassLoader classLoader=ClassLoaderUtils.getCurrentClassLoader();
  T result=(T)java.lang.reflect.Proxy.newProxyInstance(classLoader,new Class[]{interfaceClass},handler);
  return result;
}
