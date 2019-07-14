/** 
 * Injects target into proxy.
 */
public static void injectTargetIntoProxy(final Object proxy,final Object target){
  Class proxyClass=proxy.getClass();
  try {
    Field field=proxyClass.getField("$___target$0");
    field.set(proxy,target);
  }
 catch (  Exception ex) {
    throw new ProxettaException(ex);
  }
}
