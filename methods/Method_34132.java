@SuppressWarnings("unchecked") public static <T>T getProxy(Class<T> type,ObjectFactory<T> factory){
  return (T)Proxy.newProxyInstance(ProxyCreator.class.getClassLoader(),new Class<?>[]{type},new LazyInvocationHandler<T>(factory));
}
