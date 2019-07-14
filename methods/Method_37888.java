/** 
 * Shortcut for <code>getComponentTypes(type.getGenericSuperclass())</code>.
 * @see #getComponentTypes(java.lang.reflect.Type)
 */
public static Class[] getGenericSupertypes(final Class type){
  return getComponentTypes(type.getGenericSuperclass());
}
