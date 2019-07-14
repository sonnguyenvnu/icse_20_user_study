/** 
 * Returns the internal name of the given class. The internal name of a class is its fully qualified name, as returned by Class.getName(), where '.' are replaced by '/'.
 * @param clazz an object or array class.
 * @return the internal name of the given class.
 */
public static String getInternalName(final Class<?> clazz){
  return clazz.getName().replace('.','/');
}
