/** 
 * Parse proxy invoker from proxy object
 * @param proxyObject Proxy object
 * @return proxy invoker
 */
public static Invoker parseInvoker(Object proxyObject){
  InvocationHandler handler=java.lang.reflect.Proxy.getInvocationHandler(proxyObject);
  if (handler instanceof JDKInvocationHandler) {
    return ((JDKInvocationHandler)handler).getProxyInvoker();
  }
  return null;
}
