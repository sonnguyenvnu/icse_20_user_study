@SuppressWarnings("unchecked") public static <T>T create(final Handler handler,final Class<T> type,final T target){
  return (T)Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},new ThreadedCallbacks(handler,target));
}
