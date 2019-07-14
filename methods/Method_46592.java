/** 
 * Used to determine if the given class is a proxy class.
 * @param clazz the class in question
 * @return true if proxy - false otherwise
 */
public static boolean isProxy(final Class<?> clazz){
  return (clazz != null && P6Proxy.class.isAssignableFrom(clazz));
}
