public static <T extends IInterface>T get(Class<T> tClass,final ListenerPool<T> listenerPool){
  return (T)Proxy.newProxyInstance(tClass.getClassLoader(),new Class[]{tClass},new MultiListenerProxy<T>(listenerPool));
}
