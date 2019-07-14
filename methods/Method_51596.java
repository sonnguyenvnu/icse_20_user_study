/** 
 * Returns the type(class) for the name specified or null if not found.
 * @param name String
 * @return Class
 */
public static Class<?> getPrimitiveTypeFor(String name){
  return PRIMITIVE_TYPE_NAMES.typeFor(name);
}
