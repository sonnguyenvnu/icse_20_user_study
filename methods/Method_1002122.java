public static <T extends IInterface>T get(Context context,Intent intent,Class<T> tClass,String bindAction){
  return (T)Proxy.newProxyInstance(tClass.getClassLoader(),new Class[]{tClass},new RemoteListenerProxy<T>(context,intent,tClass,bindAction));
}
