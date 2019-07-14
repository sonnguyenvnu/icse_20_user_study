@NonNull public static <T>T createStub(Class<T> interfaceClass,InvocationHandler handler){
  return (T)Proxy.newProxyInstance(StubCreator.class.getClassLoader(),new Class[]{interfaceClass},handler);
}
