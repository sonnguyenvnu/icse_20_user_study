public static Invoker parseInvoker(Object proxyObject){
  try {
    Field field=proxyObject.getClass().getField("handler");
    if (!field.isAccessible()) {
      field.setAccessible(true);
    }
    BytebuddyInvocationHandler interceptor=(BytebuddyInvocationHandler)field.get(proxyObject);
    return interceptor.getProxyInvoker();
  }
 catch (  Exception e) {
    return null;
  }
}
