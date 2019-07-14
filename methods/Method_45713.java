/** 
 * The isAssignableFrom method which can cross multiple classloader.
 * @param interfaceClass ???
 * @param implementClass ???
 * @return ??????????
 * @see Class#isAssignableFrom(Class) 
 */
public static boolean isAssignableFrom(Class<?> interfaceClass,Class<?> implementClass){
  if (interfaceClass.isAssignableFrom(implementClass)) {
    return true;
  }
  String interfaceName=interfaceClass.getCanonicalName();
  return implementClass.getCanonicalName().equals(interfaceName) || isImplementOrSubclass(interfaceName,implementClass);
}
