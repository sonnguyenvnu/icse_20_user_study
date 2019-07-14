/** 
 * Parse proxy invoker from proxy object
 * @param proxyObject Proxy object
 * @return proxy invoker
 */
public static Invoker parseInvoker(Object proxyObject){
  try {
    Field field=proxyObject.getClass().getField("proxyInvoker");
    if (!field.isAccessible()) {
      field.setAccessible(true);
    }
    return (Invoker)field.get(proxyObject);
  }
 catch (  Exception e) {
    return null;
  }
}
