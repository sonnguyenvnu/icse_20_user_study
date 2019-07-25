public static Object unwrap(Object object){
  if (object instanceof CompatProxy) {
    return ((ReflectiveInvoker)Proxy.getInvocationHandler(object)).target();
  }
  return object;
}
