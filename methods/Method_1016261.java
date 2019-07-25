/** 
 * A small hack to pump an event. Reflection calls in this method has to be replaced once Sun provides a public API to pump events.
 * @throws Exception
 */
public void start() throws Exception {
  Class clazz=Class.forName("java.awt.Conditional");
  Object conditional=Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
  Method pumpMethod=Class.forName("java.awt.EventDispatchThread").getDeclaredMethod("pumpEvents",new Class[]{clazz});
  pumpMethod.setAccessible(true);
  pumpMethod.invoke(Thread.currentThread(),conditional);
}
